<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../includes/header.jsp"%>


<div class="container-fluid">

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Board Read</h1>
		</div>
	</div>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				
				<div class="panel-heading">Board Modify Page</div>
				
				<div class="panel-body">
				
					<form role="form" action="/board/modify" method="post">
						<div class="form-group">
							<label>Bno</label>
							<input class="form-control" name='bno' value='<c:out value="${board.bno }" />' readonly="readonly" />
						</div>
					
						<div class="form-group">
							<label>RegDate</label>
							<fmt:parseDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm" var="regdate"/>
							<input class="form-control" name='regDate' value='<fmt:formatDate value="${regdate}" pattern="yyyy/MM/dd"/>' readonly="readonly" />
						</div>
						
						<div class="form-group">
							<label>Update Date</label>
							<fmt:parseDate value="${board.updateDate}" pattern="yyyy-MM-dd HH:mm" var="updateDate"/>
							<input class="form-control" name='updateDate' value='<fmt:formatDate value="${updateDate}" pattern="yyyy/MM/dd"/>' readonly="readonly" />						</div>					
					
						<div class="form-group">
							<label>Title</label>
							<input class="form-control" name='title' value='<c:out value="${board.title }" />' />
						</div>
						<div class="form-group">
							<label>Text Area</label>
							<textarea class="form-control" rows="3" name="content"><c:out value="${board.content }" /></textarea>
						</div>
						<div class="form-group">
							<label>Writer</label>
							<input class="form-control" name="writer" value='<c:out value="${board.writer }" />' readonly="readonly" />
						</div>
						<button type="submit" data-oper="modify" class="btn btn-default">Modify</button>
						<button type="submit" data-oper="remove" class="btn btn-default">Remove</button>
						<button type="submit" data-oper="list" class="btn btn-info">List</button>
					</form>
				
				</div>
				<!-- end panel-body -->
				
			</div>
			<!-- end panel -->
		</div>
		<!-- end col-lg-12 -->
	</div>
	<!-- end row -->
</div>
<!-- end container-fluid -->
<%@include file="../includes/footer.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		
		var formObj = $("form");
		
		$('button').on("click", function(e) {
			
			e.preventDefault();
			
			var operation = $(this).data("oper");
			
			console.log(operation);
			
			if(operation === 'remove') {
				formObj.attr("action", "/board/remove");	
			} else if(operation === 'list') {
				//move to list
				formObj.attr("action", "/board/list").attr("method", "get");
				formObj.empty();
			}
			
			formObj.submit();
		});
		
	});
</script>
