<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
div.todo_body {
	width: 60%;
	margin: 10px auto;
	padding: 2rem;
	float: right;
}

div.todo_content {
	cursor: pointer;
}

div.complete {
	text-decoration: line-through wavy;
	color: green;
}
</style>
<div class="todo_body w3-card-4">
	<sec:authorize access="isAuthenticated()">
		<h1 class="w3-text-blue">
			[
			<sec:authentication property="principal.username" />
			] 님의 TODO LIST
		</h1>
		<table class="food">
			<thead>
				<tr>
					<th>SEQ</th>
					<th>제목</th>
					<th>작성날짜</th>
					<th>작성시각</th>
				</tr>
				<c:forEach items="${FOODS}" var="FOOD">
					<tr data-seq="${FOOD.b_seq}">
						<td>${FOOD.b_seq}</td>
						<td>${FOOD.b_subject}</td>
						<td>${FOOD.b_date}</td>
						<td>${FOOD.b_time}</td>
					</tr>
				</c:forEach>
			</thead>
		</table>
		<button>
			<a href="${rootPath}/food/insert">추가하기</a>
		</button>
	</sec:authorize>
</div>
