<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8">
<title>index</title>
<link href="index.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/inc/header.jsp" />
	<div id="main">
		<h3>MAIN</h3>
		<h3>Spring Legacy + MyBatis 기본 예제</h3>
		<p>
			<a href="${pageContext.request.contextPath}/dept/list">DEPT 목록으로</a>
		</p>
	</div>
	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
</body>
</html>
