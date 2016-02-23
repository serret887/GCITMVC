<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>


<%@page
	import="com.gcit.training.spring.lms.service.AdminstratorProfile"%>
<%@page import="com.gcit.training.spring.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.spring.lms.entity.LibraryBranch"%>
<%@page import="com.gcit.training.spring.lms.entity.Genre"%>
<%@page import="com.gcit.training.spring.lms.entity.Author"%>
<%@page import="com.gcit.training.spring.lms.entity.Publisher"%>

<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<html>
<head>
<meta charset="ISO-8859-1">

<!-- select -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
<!-- select -->
</head>

<%
	Integer bookId = Integer.parseInt(request.getParameter("bookId"));
	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	AdminstratorProfile service = (AdminstratorProfile) ac.getBean("adminProfile");
	Book book = service.getBookById(bookId);
	List<Author> authors = service.getAuthorsSimpleData(1);
	List<Genre> genres = service.getGenres(1);
	List<Publisher> publishers = service.getPublishers(1);
%>

<body>
	<div>
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">Edit Book</h4>
		</div>

		<form action="Administrator/EditBook" method="post">
			<div class="col-xs-6">
				<label for="aut">Enter Updated Book Title:</label><br /> <input
					type="text" name="name" class="form-control"
					value=<%=book.getTitle()%>> <br />
			</div>
			<div class="col-xs-6">
				<input type="hidden" name="bookId" value=<%=book.getBookId()%>>
				<label for="aut">Select Author</label><br /> <select id="aut"
					class="selectpicker" multiple="multiple" name="authorIds">
					<%
						for (Author a : authors) {
					%>
					
					<option
						value=<%out.print(a.getAuthorId());
				//TODO if the book is in the author repertory need to show selected%>>
						<%
							out.print(a.getAuthorName());
						%>
					</option>
					<%
						}
					%>
				</select> <br /> <label for="gen">Select Genres</label><br /> <select
					id="gen" class="selectpicker" multiple="multiple" name="generIds">
					<%
						for (Genre g : genres) {
					%>
					
					
					<option
						value=<%out.print(g.getGenreId());
				//TODO if the book is in the author repertory need to show selected%>>
						<%
							out.print(g.getGenreName());
						%>
					</option>
					<%
						}
					%>
				</select> <br /> <label for="pub">Select publisher</label><br /> <select
					class="selectpicker" size="1" name="pubId" size="1">
					<%
						for (Publisher p : publishers) {
					%>
					<option
						value=<%out.print(p.getPublisherId());
				//TODO if the book is in the author repertory need to show selected%>>
						<%
							out.print(p.getPublisherName());
						%>
					</option>
					<%
						}
					%>
				</select> 		<br />	<br />
			</div>
		
			<div class="modal-footer">
			<br />	<br />
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="submit" class="btn btn-primary">EDIT</button>
			</div>
		</form>
	</div>
	<script>
		$(document).ready(function() {
			$('.selectpicker').selectpicker();
		});
	</script>
</body>
</html>