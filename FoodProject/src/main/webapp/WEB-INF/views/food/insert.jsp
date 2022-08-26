<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
	form#bbsVO {
		width: 60%;
		margin: 10px auto;
		float: right;
	}
	
	#bbsVO input, #bbsVO textarea {
		width: 90%;
		margin:5px auto;
		padding:8px;
	}
</style>
	<form:form modelAttribute="bbsVO" action="${rootPath}/food/insert?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
		<form:input path="b_subject" placeholder="제목을 입력해수세요"/>
		<form:input path="b_addr" placeholder="주소를 입력하세요"/>
		<form:textarea path="b_content" placeholder="내용을 입력해주세요"/>
		<input type="file" name="mFile"  accept="images/*"/>
		<button>작성완료</button>
	</form:form>