<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<head>
	<link href="/resources/css/board/board.css" rel="stylesheet"> 
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="content container">   
    <h2 class="tit">*게시판</h2>
    <div class="desc_board">
      <div class="info" >
          <span>번호 : ${board.bdIdx}</span>
          <span>제목 : ${board.title}</span>
          <span>등록일 : ${board.regDateAsDate}</span>
          <span>작성자 : ${board.userId}</span>
      </div>
      <div class="info file_info">
      	<ol>
      	<c:forEach var="file" items="${files}">
      		<li>  
      	 		<a href="/board/download?flIdx=${file.flIdx}">${file.originFileName}</a> 
      	 	</li>	     
      	</c:forEach>
      	</ol>
      </div>
      <div class="article_content">
      	<pre>${board.content}</pre>
      </div>
      <div class="btn_section">
      
          <button onclick="location.href='/board/list'"><span>목록</span></button>
          
          <c:if test="${ not empty sessionScope.auth and board.userId == sessionScope.auth.userId }">
          <form action="/board/remove" method="post">
          	<input type="hidden" value="${board.bdIdx}" name="bdIdx"/> 
          	<button id="btnDel"><span>삭제</span></button>
          </form>
          <form action="/board/modify" method="get">
          	<input type="hidden" value="${board.bdIdx}" name="bdIdx"/> 
          	<button id="btnModify"><span>수정</span></button>
          </form>	
          </c:if>
      </div>
   </div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>


</body>
</html>