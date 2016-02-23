<!DOCTYPE html>
<%@page import="com.gcit.training.spring.lms.service.LibrarianProfile"%>
<%@page import="com.gcit.training.spring.lms.entity.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.spring.lms.entity.Author"%>
<%@page import="com.gcit.training.spring.lms.entity.Genre"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@include file="../include.html"%>
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
	LibrarianProfile service = (LibrarianProfile) ac.getBean("librarianProfile");

	List<LibraryBranch> libraries = service.getLibraries(pageNo);
%>

<center></center><table class="table table-striped table-bordered table-hover col-md-6 ">
	<tr>
		<th><center>Name</center></th>
		<th><center>Address</center></th>
		<th><center>Edit</center></th>
	</tr>

	<%
		for (LibraryBranch l : libraries) {
	%>
	<tr>
		<td><center><a	href='Librarian/AddBooksToLibrary?branchId=<%=l.getBranchId()%>&libraryName=<%=l.getBranchName()%>' />
			<%=l.getBranchName()%></center></td>
		<td><center><%=l.getBranchAddress()%></center></td>
		<td>
		
	<center>	<button type="button" class="btn btn-info" data-toggle="modal"
					data-target="#myModal44"
					href="Librarian/EditLibrary?branchId=<%=l.getBranchId()%>">EDIT</button></center>
		</td>
	</tr>
	<%
		}
	%>
<div id="myModal44" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content"></div>
		</div>
	</div>
</table></center>


</body>
</html>