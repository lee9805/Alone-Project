<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
	div.foodList {
		float: right;
		margin-right: 30%; 
	}
</style>
<div class="foodList">
	<h2>제목 : ${BBS.b_subject}</h2>
	<div>주소 : ${BBS.b_addr}</div>
	<div>내용 : ${BBS.b_content}</div>
	<button><a href="${rootPath}/food/update/${BBS.b_seq}">수정하기</a></button>
	<button><a href="${rootPath}/food/delete/${BBS.b_seq}">삭제하기</a></button>
</div>
