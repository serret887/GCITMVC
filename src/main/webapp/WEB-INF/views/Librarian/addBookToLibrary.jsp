<!DOCTYPE html>
<%@page import="com.gcit.training.spring.lms.service.LibrarianProfile"%>
<%@page import="com.gcit.training.spring.lms.entity.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.spring.lms.entity.Book"%>
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
<a	href='Librarian'><h1>Librarian Menu</h1></a>
<%
	Integer pageNo = (int) request.getAttribute("pageNo");
	if (pageNo == null)
		pageNo = 1;
	Integer branchId = (int) request.getAttribute("branchId");

	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	LibrarianProfile service = (LibrarianProfile) ac.getBean("librarianProfile");

	List<Book> books = service.getAllBooksInLibrary(branchId);
%>

<title>Insert title here</title>
</head>
<body>

	<h2>
		List of Books in:<%=service.getLibraryName(branchId)%></h2>
<body>

	<table class="table table-striped table-bordered table-hover">
		<tr>
			<th>Book Title</th>
			<th>Publisher</th>
			<th>Amount</th>
			<th>Amount to add</th>
		</tr>
		<%
			for (Book b : books) {
		%>
		<tr>
			<td align="center"><%=b.getTitle()%></td>
			<td align="center"><%=b.getPublisher().getPublisherName()%></td>
			<td align="center">
				<%
					out.print(service.getAmountOfBookByBranch(b.getBookId(), branchId));
				%>
			</td>

			<form method="post" action="Librarian/AddBooksToLibrary">
				<td align="center"><input type="text" name="amount"
					placeholder="Insert quantity to add"> <input type="hidden"
					name="branchId" value=<%=branchId%>></td>
				<td><input type="hidden" name="bookId" value=<%=b.getBookId()%>>
			
				
					<button class="btn btn-success" type="submit">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					</button>
			</form>
			</td>
		</tr>
		<%
			}
		%>

	</table>
</body>
</html>