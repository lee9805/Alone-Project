package com.callor.food.service.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.callor.food.model.AuthorVO;
import com.callor.food.model.UserVO;
import com.callor.food.persistance.UserDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("authenticationProvider")
public class AuthenticationProviderImpl implements AuthenticationProvider {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		// 로그인을 수행한 사용자의 ID 와 비번 추출
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		UserVO userVO = userDao.findById(username);
		log.debug("로그인 한 사용자 {}", userVO);
		
		if(userVO == null) {
			throw new UsernameNotFoundException(username + " 이 없습");
		}
		
		// DB 에 저장된 암호화된 비번 getter
		String encPassword = userVO.getPassword();
		
		if(  !passwordEncoder.matches(password, encPassword)  ) {
			throw new BadCredentialsException("비밀번호 오류!!");
		}
		
		List<AuthorVO> authList = userDao.select_auths(username);
		List<GrantedAuthority> grantList = new ArrayList<>();
		
		for(AuthorVO vo : authList) {
			grantList.add(new SimpleGrantedAuthority(vo.getAuthority()));
		}
	

		return new UsernamePasswordAuthenticationToken(
				userVO, null,grantList);


	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
