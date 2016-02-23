<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page
	import="com.gcit.training.spring.lms.service.AdminstratorProfile"%>
<%@page import="com.gcit.training.spring.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.spring.lms.entity.Author"%>
<%@page import="com.gcit.training.spring.lms.entity.Genre"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>


<!-- select -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
<!-- select -->



<html>
<head>
<meta charset="ISO-8859-1">
</head>

<%
	Integer autorId = Integer.parseInt(request.getParameter("authorId"));
	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	AdminstratorProfile service = (AdminstratorProfile) ac.getBean("adminProfile");
	List<Book> books = service.getBooksFullData(-1);//TODO ONLY SHOWING FIRST PAGE
	Author author = service.getAuthorById(autorId);
%>
<body>

</body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">Edit Author</h4>
</div>

<div class="modal-body">
<form action="Administrator/EditAuthor" method="post">
	<div class="form-group">
	<div class="col-sm-6 col-lg-6 col-md-6">
			<label for="bname">Enter Author Name:</label> <input id="bname"
				type="text" class="form-control" value="<%=author.getAuthorName()%>"
				name="name">
			<center>
				<input type="hidden" name="authorId" value="<%=autorId%>" />
			</center>
		
	</div>
	
	<div class="col-sm-6 col-lg-6 col-md-6">
	<label>Select Books</label> 
	 <select class="selectpicker form-control" multiple="multiple"
		name="bookIds">
		<%
			for (Book book : books) {
		%>
		<%
			List<Book> bookA = author.getBooks();
				for (Book b : bookA) {
					if (b.getBookId() == book.getBookId()) {
						out.print("<option selected value=" + (book.getBookId()) + ">" + book.getTitle() + "</option>");
					} else {
						out.print("<option value=" + (book.getBookId()) + ">" + book.getTitle() + "</option>");
					}
				}
		%>
		<%
			}
		%>
	</select> <br /><br /><br /><br />
</div></div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		<button type="submit" class="btn btn-primary">ADD</button>
	</div>
</form>
</div>
	<script>
		$(document).ready(function() {
			$('.selectpicker').selectpicker();
		});
	</script>
</html>