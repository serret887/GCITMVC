<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page
	import="com.gcit.training.spring.lms.service.AdminstratorProfile"%>
<%@page import="com.gcit.training.spring.lms.entity.Author"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.spring.lms.entity.Author"%>
<%@page import="com.gcit.training.spring.lms.entity.Book"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@include file="../../include.html"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
</head>
<a	href='Administrator'><h1>Admin Menu</h1></a>

<form action="searchAuthors" method="post">
	<input type="text" class="col-md-3" id="searchString"
		name="searchString" placeholder="Enter Author's name to search"
		onkeyup="search();">
</form>





<%@include file="addAuthor.jsp"%>

<nav>
	<div class="pagination"></div>
</nav>
<h2>List of Authors in LMS:</h2>

<body>
	<div id="authorTable">
	</div>
	<div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content"></div>
		</div>
	</div>
</body>

<script>
	function search() {
		$.ajax({
			url : "searchAuthors",
			data : {
				searchString : $('#searchString').val()
			}
		}).done(function(data) {
			$('.pagination').html(data);
			paging(1);
			//$('.table').html(data);

		});
	}

	function paging(page) {
		$.ajax({
			url : "pageAuthors",
			data : {
				searchString : $('#searchString').val(),
				pageNo : page
			}
		}).done(function(data) {
			$('#authorTable').html(data);
		});
	}

	$(document).ready(function() {
		search();
	});
</script>







</html>