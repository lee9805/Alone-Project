<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />   

<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%> 
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>
	const rootPath="${rootPath}"
</script>
<script src="${rootPath}/static/js/detail.js?20220825010"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
	* {
	 box-sizing: border;
	 margin:0;
	 padding:0;
	}
	
	html {
		width: 100vw;
		height: 100vh;
	}
	
	body {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
	}
	
	header {
		padding:2rem;
		color:white;
		background-color: green;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	nav {
		background-color: blue;
		color:white;
	}
	
	nav ul {
		list-style: none;
		display: flex;
	}
	
	nav li {
		padding:16px 12px;
	}
	
	nav a {
		text-decoration: none;
		color:inherit;
		magin:5px 0;
		padding:0 12px;
		border-bottom:3px solid transparent;
		transition : 1s;
	}
	
	nav a:hover {
		border-bottom:3px solid #ddd
	}
	
	
	nav li:nth-of-type(4) {
		margin-left:auto;
	
	}
	
	nav li:nth-of-type(1) {
		margin-left:20px;
	}
	
	nav li:last-of-type {
		margin-right: 30px;
	}
	
	section.main {
		flex:1;
	}
	
	article.welcome {
		height:100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		float: right;
		width: 70%;
	}
	aside {
		float: left;
		width: 30%;
		height: 100%;
	}

</style>
</head>
<body>
<header>
	<h1>추억 장소 리스트</h1>
</header>
<nav>
	<ul>
		<li><a href="${rootPath}/">HOME</a></li>
		<li><a href="${rootPath}/food">나의 추억</a></li>
		
		<sec:authorize access="isAnonymous()">
			<li><a href="${rootPath}/user/login">로그인</a></li>
			<li><a href="${rootPath}/user/join">회원가입</a></li>
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">		
			<li><a href="${rootPath}/">로그아웃</a></li>
		</sec:authorize>
	</ul>
</nav>
<section class="main">
<aside>
<div id="map" style="width:100%;height:100%;"></div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c40dd76a6df03f9c54aa753cc3dbc76c&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 15 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch('광주광역시 남구 진다리로 24번길 10', function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">우리집</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});    
</script>
</aside>
	<c:choose>
		<c:when test="${LAYOUT == 'JOIN' }">
			<%@ include file="/WEB-INF/views/user/join.jsp" %>
		</c:when>
		<c:when test="${LAYOUT == 'LOGIN' }">
			<%@ include file="/WEB-INF/views/user/login.jsp" %>
		</c:when>
		<c:when test="${LAYOUT == 'FOOD_LIST' }">
			<%@ include file="/WEB-INF/views/food/list.jsp" %>
		</c:when>
		<c:when test="${LAYOUT == 'FOOD_INSERT' }">
			<%@ include file="/WEB-INF/views/food/insert.jsp" %>
		</c:when>
		<c:when test="${LAYOUT == 'FOOD_DETAIL' }">
			<%@ include file="/WEB-INF/views/food/detail.jsp" %>
		</c:when>
		<c:otherwise>
			<article class="welcome">
				<h1>2022 장소 추억 리스트</h1>
				<p>추억을 저장하시려면 회원가입, 로그인을 해 주세요
			</article>
		</c:otherwise>	
	</c:choose>
</section>
<form:form class="logout" action="${rootPath}/logout"/>
</body>
</html>