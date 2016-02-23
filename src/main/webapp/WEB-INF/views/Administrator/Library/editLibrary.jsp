<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page
	import="com.gcit.training.spring.lms.service.AdminstratorProfile"%>
<%@page import="com.gcit.training.spring.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.spring.lms.entity.LibraryBranch"%>
<%@page import="com.gcit.training.spring.lms.entity.Genre"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>

<html>
<head>
<meta charset="ISO-8859-1">
</head>

<%
	Integer branchId = Integer.parseInt(request.getParameter("branchId"));
	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	AdminstratorProfile service = (AdminstratorProfile) ac.getBean("adminProfile");
	LibraryBranch branch = service.getLibraryById(branchId);
%>
<body>

</body>
<div class="modal-header">
	<h2>Enter Library Details to Edit:</h2>
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">Insert Card Number</h4>
</div>
<form action="Administrator/EditLibrary" method="post">
	<div class="form-group">
		<label for="bname">Enter Library Name:</label> 
		<input id="bname"
			type="text" class="form-control" value="<%=branch.getBranchName()%>"
			name="name"><br />
			 <input type="hidden" name="branchId"
			value="<%=branchId%>" /> 
			<label for="baddress"> Enter Updated Library Address: </label>
			
			<input class="form-control" type="text"
			name="address" value=<%=branch.getBranchAddress()%>><br />
			
	</div>
	<br /> <br />
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		<button type="submit" class="btn btn-primary">EDIT</button>
	</div>
</form>
</div>
</html>
