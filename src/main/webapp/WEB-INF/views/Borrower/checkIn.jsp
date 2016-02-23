<!DOCTYPE html>
<%@page import="com.gcit.training.spring.lms.service.BorrowerProfiles"%>
<%@page import="com.gcit.training.spring.lms.entity.Book"%>
<%@page import="com.gcit.training.spring.lms.entity.Books_Loans"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.spring.lms.entity.Author"%>
<%@page import="com.gcit.training.spring.lms.entity.Genre"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@include file="./../include.html"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
</head>

<%
	int cardNo = (int) request.getAttribute("cardNo");
	int pageNo = (int) request.getAttribute("pageNo");
	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	BorrowerProfiles service = (BorrowerProfiles) ac.getBean("borrowerProfile");

	List<Books_Loans> bookloans = service.getAllBookLoans(cardNo);
%>
<a	href="Borrower/CheckValidCard?cardNo=<%=cardNo %>&pageNo=1"><h1>Go Back</h1></a>
	<h2>List of Books in LMS:</h2>
<body>
<table class="table table-striped table-bordered table-hover">
		<tr>
			<th>Book Title</th>
			<th>Library Branch</th>
			<th>Date Out</th>
			<th>Due Date</th>
			<th>Date In</th>
			<th>Check In</th>
		</tr>
		<tr>
			<%
				for (Books_Loans bl : bookloans) {
			%>
			<td align="center"><%=bl.getBook().getTitle()%></td>
			<td align="center"><%=bl.getBranch().getBranchName()%></td>
			<td align="center"><%=bl.getDateOut()%></td>
			<td align="center"><%=bl.getDueDate()%></td>
			<td align="center"><%=bl.getDateIn()%></td>
			<td>
				<form method="post" action="Borrower/CheckIn">
					<input type="hidden" name="bookId" value=<%=bl.getBook().getBookId()%> /> <input
						type="hidden" name="branchId" value=<%=bl.getBranch().getBranchId()%> /> <input
						type="hidden" name="cardNo" value=<%=cardNo%> />
						<input
						type="hidden" name="pageNo" value=<%=pageNo%> />
					<button class="btn btn-success" type="submit">Check In</button>
				</form>
			</td>
		</tr>
		<%
			}
		%>


	</table>


</body>


</html>