<!DOCTYPE html>
<%@page
	import="com.gcit.training.spring.lms.service.AdminstratorProfile"%>
<%@page import="com.gcit.training.spring.lms.entity.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.spring.lms.entity.Author"%>
<%@page import="com.gcit.training.spring.lms.entity.Genre"%>
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
<%
	Integer pageNo = (int) request.getAttribute("pageNo");
	if (pageNo == null)
		pageNo = 1;
	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	AdminstratorProfile service = (AdminstratorProfile) ac.getBean("adminProfile");

	List<Publisher> publishers = service.getPublishers(pageNo);
%>

<%@include file="addPublisher.jsp"%>

<h2>List of Publishers in LMS:</h2>
<body>
	<table class="table table-striped table-bordered table-hover">
		<tr>
			<th>Publisher ID</th>
			<th>Publisher Name</th>
			<th>Publisher Address</th>
			<th>Publisher Phone</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%
			for (Publisher p : publishers) {
		%>
		<tr>
			<td align="center"><%=p.getPublisherId()%></td>
			<td align="center"><%=p.getPublisherName()%></td>
			<td align="center"><%=p.getPublisherAddress()%></td>
			<td align="center"><%=p.getPublisherPhone()%></td>
			<td>
				<button type="button" class="btn btn-info" data-toggle="modal"
					data-target="#myModal13"
					href="Administrator/EditPublisher?pubId=<%=p.getPublisherId()%>">EDIT</button>


			</td>
			<td>
				<form method="post" action="Administrator/DeletePublisher">
					<input type="hidden" name="pubId" value=<%=p.getPublisherId()%>>
					<button class="btn btn-danger" type="submit">Delete</button>
				</form>
			</td>
			<%
				}
			%>
		
	</table>
	<div class="col-sm-6 col-lg-6 col-md-6">
		<div id="myModal13" class=" modal fade" tabindex="-1" role="dialog"
			aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg">
				<div class="modal-content"></div>
			</div>
		</div>
	</div>
</body>
</html>