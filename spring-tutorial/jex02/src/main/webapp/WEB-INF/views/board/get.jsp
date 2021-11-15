<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../includes/header.jsp"%>

<style type="text/css">
	.chat { list-style:none; }
	.chat li:after {
        display: block;
        width: 100%;
        border-bottom: 1px solid #bcbcbc;
        margin: 20px 0px;
	}
</style>

<div class="container-fluid">

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Board Read</h1>
		</div>
	</div>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				
				<div class="panel-heading">Board Read Page</div>
				<div class="panel-body">
					<div class="form-group">
						<label>Bno</label>
						<input class="form-control" name='bno' value='<c:out value="${board.bno }" />' readonly="readonly" />
					</div>
				
					<div class="form-group">
						<label>Title</label>
						<input class="form-control" name='title' value='<c:out value="${board.title }" />' readonly="readonly" />
					</div>
					<div class="form-group">
						<label>Text Area</label>
						<textarea class="form-control" rows="3" name="content" readonly="readonly"><c:out value="${board.content }" /></textarea>
					</div>
					<div class="form-group">
						<label>Writer</label>
						<input class="form-control" name="writer" value='<c:out value="${board.writer }" />' readonly="readonly" />
					</div>
					<button data-oper="modify" class="btn btn-default">Modify</button>
					<button data-oper="list" class="btn btn-info">List</button>
					
					<form id="operForm" action="/board/modify" method="get">
						<input type="hidden" id="bno" name="bno" value='<c:out value="${board.bno }" />' />
					</form>
				</div>
				<!-- end panel-body -->
				
			</div>
			<!-- end panel -->
		</div>
		<!-- end col-lg-12 -->
	</div>
	<!-- end row -->
	
	<br /><br />
	<!-- 댓글 영역 -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<i class="fa fa-comments fa-fw"></i> Reply
					<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right float-right">New Reply</button>
				</div>
				<br />
				<!-- end panel-heading  -->
				
				<div class="panel-body">
					<ul class="chat">
						<!-- start reply -->
						<li class="left clearfix" data-rno='12'>
							<div>
								<div class="header">
									<strong class="primary-font">user00</strong>
									<small class="form-text text-muted float-right">2021-06-12 15:15 </small>
								</div>
								<p>Good job!</p>
							</div>
						</li>
						<!-- end reply -->
					</ul>
					<!-- end ul -->
				</div>
				<!-- end panel-body -->
				
				<div class="panel-footer">
				
				</div>
			</div>
			<!-- end panel -->
		</div>
	</div>
	<!-- end row -->
	
	<!-- Modal 추가 -->
	<div class="modal fade" id="replyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">Reply Modal</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>Reply</label>
						<input class="form-control" name="reply" value="New Reply!!!" />
					</div>
					<div class="form-group">
						<label>Replyer</label>
						<input class="form-control" name="replyer" value="replyer" />
					</div>
					<div class="form-group">
						<label>Reply Date</label>
						<input class="form-control" name="replyDate" value="" />
					</div>
				</div>
				<div class="modal-footer">
					<button id="modalModBtn" type="button" class="btn btn-warning" data-dismiss="modal">Modify</button>
					<button id="modalRemoveBtn" type="button" class="btn btn-danger" data-dismiss="modal">Remove</button>
					<button id="modalRegisterBtn" type="button" class="btn btn-primary" data-dismiss="modal">Register</button>
					<button id="modalCloseBtn" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
</div>
<!-- end container-fluid -->
<%@include file="../includes/footer.jsp"%>

<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		var operForm = $("#operForm");
		
		$("button[data-oper='modify']").on("click", function(e){
			operForm.attr("action", "/board/modify").submit();
		});
		
		$("button[data-oper='list']").on("click", function(e){
			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list");
			operForm.submit();
		});
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		var bnoValue = '<c:out value="${board.bno}" />';
		
		// for replyService add test
		/* replyService.add(
			{reply: "JS Test", replyer:"tester", bno:bnoValue},
			
			function(result) {
				alert("RESULT : " + result);
			}
		); */
		
		// reply list test
		/* replyService.getList({bno: bnoValue, page:1}, function(list){
			for(var i = 0, len = list.length || 0; i < len; i++) {
				console.log(list[i]);
			}
		}); */
		
		// reply remove test
		/* replyService.remove(rno, function(count) {
			console.log(count);
			
			if(count === "success") {
				alert("REMOVED");
			}
		}, function(err) {
			alert("ERROR....");
		}); */
		
		// reply update test
		/*  replyService.update({
			rno: rno,
			bno: bnoValue,
			reply: "Modifed Reply...."
		}, function(result){
			alert("수정 완료...");
		}); */
		
		// reply get test
		/* replyService.get(rno, function(data) {
			console.log(data);
		}); */
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		var bnoValue = '<c:out value="${board.bno}"/>';
		var replyUL = $(".chat");
		
		showList(1);
		
		function showList(page) {
			replyService.getList({bno:bnoValue, page: page || 1}, function(replyCnt, list) {
				console.log("replyCnt: " + replyCnt);
				console.log("list: " + list);
				console.log(list);
				
				if(page == -1) {
					pageNum = Math.ceil(replyCnt/10.0);
					showList(pageNum);
					return;
				}
				
				var str = "";
				
				if(list == null || list.length == 0) {
					replyUL.html("");
					
					return;
				}
				for(var i = 0, len = list.length || 0; i < len; i++) {
					str += "<li class='left clearfix' data-rno='" + list[i].rno + "'>";
					str += "	<div>"
					str += "		<div class='header'>" 
					str += "			<strong class='primary-font'>" + list[i].replyer + "</strong>";
					str += "			<small class='form-text text-muted float-right'>" + replyService.displayTime(list[i].replyDate) + "</small>";
					str += "		</div>";
					str += "		<p>" + list[i].reply + "</p>";
					str += "	</div>";
					str += "</li>";
				}
				replyUL.html(str);
				
				showReplyPage(replyCnt);
			}); // end function
		} // end showList
		
		var modal = $("#replyModal");
		var modalInputReply = modal.find("input[name='reply']");
		var modalInputReplyer = modal.find("input[name='replyer']");
		var modalInputReplyDate = modal.find("input[name='replyDate']");
		
		var modalModBtn = $("#modalModBtn");
		var modalRemoveBtn = $("#modalRemoveBtn");
		var modalRegisterBtn = $("#modalRegisterBtn");
		
		$("#addReplyBtn").on("click", function(e) {
			modal.find("input").val('');
			modalInputReplyDate.closest("div").hide();
			modal.find("button[id != 'modalCloseBtn']").hide();
			
			modalRegisterBtn.show();
			
			$("#replyModal").modal("show");
		});
		
		modalRegisterBtn.on("click", function(e) {
			var reply = {
					reply : modalInputReply.val(),
					replyer : modalInputReplyer.val(),
					bno : bnoValue
			};
			replyService.add(reply, function(result) {
				alert(result);
				
				modal.find("input").val('');
				modal.modal("hide");
				
				//showList(1);
				showList(-1);
			});
		});
		
		$(".chat").on("click", "li", function(e) {
			var rno = $(this).data("rno");
			
			replyService.get(rno, function(reply) {
				
				modalInputReply.val(reply.reply);
				modalInputReplyer.val(reply.replyer).attr("readonly", "readonly");
				modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
				modal.data("rno", reply.rno);
				
				modal.find("button[id != 'modalCloseBtn']").hide();
				modalModBtn.show();
				modalRemoveBtn.show();
				
				$("#replyModal").modal("show");
			});
		});
		
		
		modalModBtn.on("click", function(e) {
			var reply = {rno:modal.data("rno"), reply: modalInputReply.val()};
			
			replyService.update(reply, function(result){
				alert(result);
				modal.modal("hide");
				showList(1);
			});
		});
		
		modalRemoveBtn.on("click", function(e) {
			var rno = modal.data("rno");
			
			replyService.remove(rno, function(result) {
				alert(result);
				modal.modal("hide");
				showList(1);
			});
		});
		
		//showReplyPage()
		var pageNum = 1;
		var replyPageFooter = $(".panel-footer");
		
		function showReplyPage(replyCnt) {
			var endNum = Math.ceil(pageNum / 10.0) * 10;
			var startNum = endNum - 9;
			
			var prev = startNum != 1;
			var next = false;
			
			if(endNum * 10 >= replyCnt) {
				endNum = Math.ceil(replyCnt/10.0);
			}
			
			if(endNum * 10 < replyCnt) {
				next = true;
			}
			
			var str = "<ul class='pagination pull-right float-right'>";
			
			if(prev) {
				str += "<li class='paginate_button page-item'><a class='page-link' href='" + (startNum - 1) + "'>Previous</a></li>";	
			}
			
			for(var i = startNum; i <= endNum; i++){
				var active = pageNum == i ? "active" : "";
				
				str += "<li class='paginate_button page-item " + active + "'><a class='page-link' href='" + i +"'>" + i + "</a></li>";
			}
			
			if(next) {
				str += "<li class='paginate_button page-item'><a class='page-link' href='" + (endNum + 1) + "'>Next</a></li>";
			}
			
			str += "</ul></div>";
			
			console.log(str);
			
			replyPageFooter.html(str);
		}
		
		replyPageFooter.on("click", "li a", function(e){
			e.preventDefault();  // <a> 태그의 기본 동작을 제한
			console.log("page click");
			
			var targetPageNum = $(this).attr("href");
			
			console.log("targetNum: " + targetPageNum);
			
			pageNum = targetPageNum;
			
			showList(pageNum);
		});
		
		modalModBtn.on("click", function(e){
			var reply = {rno: modal.data("rno"), reply: modalInputReply.val()};
			
			replyService.update(reply, function(result){
				modal.modal("hide");
				showList(pageNum);
			});
		});
		
		modalRemoveBtn.on("click", function(e){
			var rno = modal.data("rno");
			
			replyService.remove(rno, function(result){
				alert(result);
				modal.modal("hide");
				showList(pageNum);
			});
		});
	});
</script>

