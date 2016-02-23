<!DOCTYPE html>
<%@page
	import="com.gcit.training.spring.lms.service.AdminstratorProfile"%>
<%@page import="com.gcit.training.spring.lms.entity.Borrower"%>
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
<h1>Welcome to GCIT Library Management System</h1>
<%
	Integer pageNo = (int) request.getAttribute("pageNo");
	if (pageNo == null)
		pageNo = 1;
	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	AdminstratorProfile service = (AdminstratorProfile) ac.getBean("adminProfile");

	List<Borrower> borrowers = service.getBorrowers(pageNo);
%>

<%@include file="addBorrower.jsp"%>


<h2>List of Borrowers in LMS:</h2>
<body>
	<table class="table table-striped table-bordered table-hover">
		<tr>
			<th>Card No</th>
			<th>Borrower Name</th>
			<th>Borrower Address</th>
			<th>Borrower Phone</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%
			for (Borrower b : borrowers) {
		%>
		<tr>
			<td align="center"><%=b.getCarNo()%></td>
			<td align="center"><%=b.getName()%></td>
			<td align="center"><%=b.getAddress()%></td>
			<td align="center"><%=b.getPhone()%></td>

			<td>
				<button type="button" class="btn btn-info" data-toggle="modal"
					data-target="#myModalborrower"
					href="Administrator/EditBorrower?cardNo=<%=b.getCarNo()%>">EDIT</button>
			<td>
			<form method="post" action="Administrator/DeleteBorrower">
					<input type="hidden" name="cardNo" value=<%=b.getCarNo()%>>
					<button class="btn btn-danger" type="submit">Delete</button>
				</form></td>
			<%
				}
			%>
		
	</table>

</body>
<div id="myModalborrower" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"></div>
	</div>
</div>
</html>