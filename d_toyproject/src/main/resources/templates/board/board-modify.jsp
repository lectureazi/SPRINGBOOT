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
		<form action="${context}/board/modify" id="frmModify" class="frm_modify" method="post" enctype="multipart/form-data">
			<input type="hidden" value="${board.bdIdx}" name="bdIdx"> 
			<div >
				<div class="tit_board">
					제목 :  <input type="text" name="title" class="mt-2" required="required" value='<c:out value="${board.title}"/>'/><br>
					작성자 : <span><c:out value="${board.userId}"/></span><br>
					파일 :  <input type="file" class="mt-2" name="fileList" id="contract_file" multiple/>
				</div>
				<div class='info file_info'>
					<ol>
				      	<c:forEach var="file" items="${files}">
				      		<li id="f${file.flIdx}">  
				      	 		<span>${file.originFileName} 
				      	 		<b data-idx="f${file.flIdx}" class="btnClose">x</b></span> 
				      	 	</li>	     
				      	</c:forEach>
			      	</ol>
				</div>
				<div class="content_board">
					<textarea name="content" required="required" ><c:out value="${board.content}"/></textarea>
				</div>
				<div class="btn_section">
					<button style="background-color:red; color:white; width:100%">전송</button>
				</div>
			</div>

		</form>
	</div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>

<script type="text/javascript">

document.querySelectorAll('.btnClose').forEach(e => {
	
	e.addEventListener('click', ev => {
		
		let fileId = ev.target.dataset.idx;
		document.querySelector('#'+fileId).style.display='none';
		
		fileId = fileId.replace('f','');
		
		inpDelfile = createElement("input",{prop:{"type":"hidden","value":fileId,"name":"delFiles"}});
		frmModify.append(inpDelfile);
		
	});
})

</script>







</body>
</html>