<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container wrap_content">
	<ul>
		<li>${auth.userId}</li>
		<li>${auth.password}</li>
		<li>${auth.email}</li>
	</ul>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>