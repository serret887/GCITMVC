<!DOCTYPE html>
<%@page
	import="com.gcit.training.spring.lms.service.AdminstratorProfile"%>
<%@page import="com.gcit.training.spring.lms.entity.LibraryBranch"%>
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

	List<LibraryBranch> libraries = service.getLibraries(pageNo);
%>

<%@include file="addLibrary.jsp"%>

<table class="table table-striped table-bordered table-hover">
	<tr>
		<th>Name</th>
		<th>Address</th>
		<th>Edit</th>
		<th>Delete</th>

	</tr>

	<%
		for (LibraryBranch l : libraries) {
	%>
	<tr>
		<td><%=l.getBranchName()%></td>
		<td><%=l.getBranchAddress()%></td>
		<td>
			<button type="button" class="btn btn-info" data-toggle="modal"
				data-target="#myModal133"
				href="Administrator/EditLibrary?branchId=<%=l.getBranchId()%>">EDIT</button>
		<td><form method="post" action="Administrator/DeleteLibrary">
				<input type="hidden" class="btn btn-danger" name="branchId"
					value=<%=l.getBranchId()%>>
				<button class="btn btn-danger" type="submit">Delete</button>
			</form></td>
	</tr>
	<%
		}
	%>

</table>
<div id="myModal133" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"></div>
	</div>
</div>

</body>
</html>