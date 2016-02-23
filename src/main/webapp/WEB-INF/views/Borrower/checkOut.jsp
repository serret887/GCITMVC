<!DOCTYPE html>
<%@page import="com.gcit.training.spring.lms.service.BorrowerProfiles"%>
<%@page import="com.gcit.training.spring.lms.entity.Book"%>
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

<h1>Welcome to GCIT Library Management System</h1>
<%
	int cardNo = (int) request.getAttribute("cardNo");
	int branchId = (int) request.getAttribute("branchId");

	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	BorrowerProfiles service = (BorrowerProfiles) ac.getBean("borrowerProfile");

	List<Book> books = service.getAllBooksAvailablesInBranch(cardNo, branchId);
%>
<a	href="Borrower/CheckValidCard?cardNo=<%=cardNo %>&pageNo=1"><h1>Go Back</h1></a>

<h2>List of Books in LMS:</h2>
<body>
<table class="table table-striped table-bordered table-hover">
		<tr>
			<th>Book Title</th>
			<th>Book Authors</th>
			<th>Publisher</th>
			<th>Check Out</th>
		</tr>
		<%
			for (Book b : books) {
		%>
		<tr>
			<td align="center"><%=b.getTitle()%></td>
			<td align="center">
				<%
					for (Author a : b.getAuthors()) {
				%> <%=a.getAuthorName()%>&nbsp; <%
 	}
 %>

			</td>

			<td align="center"><%=b.getPublisher().getPublisherName()%></td>
			<td>
				<form method="post" action="Borrower/CheckOut">
					<input type="hidden" name="bookId" value=<%=b.getBookId()%> /> <input
						type="hidden" name="branchId" value=<%=branchId%> /> <input
						type="hidden" name="cardNo" value=<%=cardNo%> />
					<button class="btn btn-success" type="submit">Check Out</button>
				</form>
			</td>

			<%
				}
			%>
		
	</table>

</body>
</html>