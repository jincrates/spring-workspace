<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@include file="../includes/header.jsp"%>
<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">Tables</h1>

	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
			<button id="regBtn" type="button" class="btn btn-primary form-row float-right">Register New Board</button>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"	cellspacing="0">
					<thead>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${list}" var="board">
						<tr>
							<td><c:out value="${board.bno}" /></td>
							<td>
								<a href='/board/get?bno=<c:out value="${board.bno}" />'><c:out value="${board.title}" /></a>
							</td>
							<td><c:out value="${board.writer}" /></td>
							<td>
								<fmt:parseDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm" var="regdate"/>
								<fmt:formatDate value="${regdate}" pattern="yyyy-MM-dd"/>		
							</td>
							<td>
								<fmt:parseDate value="${board.updateDate}" pattern="yyyy-MM-dd HH:mm" var="updateDate"/>
								<fmt:formatDate value="${updateDate}" pattern="yyyy-MM-dd"/>	
							</td>
						</tr>					
					</c:forEach>
					</tbody>
				</table>
				
				<!-- 
				<div class="dataTables_paginate paging_simple_numbers">
					<ul class="pagination">
						<c:if test="${pageMaker.prev }">
							<li class="paginate_button page-item active">
								<a href="${pageMaker.startPage - 1}" class="page-link">Previous</a>
							</li>
						</c:if>
						
						<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage }">
							<li class="paginate_button page-item ${pageMaker.cri.pageNum == num ? 'active' : ''} "><a href="${num }" class="page-link">${num }</a></li>
						</c:forEach>
						
						<c:if test="${pageMaker.next }">
							<li class="paginate_button next"><a href="${pageMaker.endPage + 1}" class="page-link">Next</a></li>
						</c:if>
					</ul>
				</div>

				<form id="actionForm" action="/board/list" method="get">
					<input type="hidden" name="pageNum" value = '${pageMaker.cri.pageNum} '>
					<input type="hidden" name="amount" value = '${pageMaker.cri.amount} '>
				</form>
				 -->
				<!-- end Pagination -->
				
			</div>
		</div>
	</div>

</div>
<!-- /.container-fluid -->

<!-- Modal 추가 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">Modal title</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">처리가 완료되었습니다.</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal">Save changes</button>
			</div>
		</div>
	</div>
</div>


<%@include file="../includes/footer.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		
		var result = '<c:out value="${result}" />';
		
		checkModal(result);
		
		history.replaceState({}, null, null);
		
		function checkModal(result) {
			
			if(result === '' || history.state) {
				return;
			}
			
			if(parseInt(result) > 0) {
				$(".modal-body").html("게시글 " + parseInt(result) + "번이 등록되었습니다.");
			}
			
			$("#myModal").modal("show");
		}
		
		$("#regBtn").on("click", function(){
			self.location = "/board/register";
		});
		
		
		/*
		var actionForm = $("#actionForm");
		
		$(".paginate_button a").on("click", function(e) {
			
			e.preventDefault();
			
			console.log('click');
			
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		})
		  */
	});
</script>
