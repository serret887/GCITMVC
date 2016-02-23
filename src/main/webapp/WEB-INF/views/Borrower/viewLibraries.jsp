<%@page import="com.gcit.training.spring.lms.entity.LibraryBranch"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@include file="./../include.html"%>
<%@page
	import="com.gcit.training.spring.lms.service.BorrowerProfiles"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Libraries</title>
</head>
<%
	int cardNo = (int) request.getAttribute("cardNo");
	int pageNo = (int) request.getAttribute("pageNo");
	
	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	BorrowerProfiles service = (BorrowerProfiles) ac.getBean("borrowerProfile");

	List<LibraryBranch> libraries = service.getAllBranches(pageNo);
	
%>
Click here to return a book:
<p>
	<button class="btn btn-info" type="button "
		onclick="javascript:location.href='Borrower/CheckIn?cardNo=<%=cardNo%>&pageNo=1'">Check In</button>
</p>
<body>
	<table class="table table-striped table-bordered table-hover" >
		<tr>
			<th>Name</th>
			<th>Address</th>
			<th>Select</th>
		</tr>

		<%
			for (LibraryBranch l : libraries) {
		%>
		<tr>
			<td><%=l.getBranchName()%></td>
			<td><%=l.getBranchAddress()%></td>
			<td>
				<button class="btn btn-primary" type="button"
					onclick="javascript:location.href='Borrower/ViewBookBranch?cardNo=<%=cardNo%>&branchId=<%=l.getBranchId()%>'">SELECT</button>
			</td>

		</tr>
		<%
			}
		%>

	</table>


</body>
</html>