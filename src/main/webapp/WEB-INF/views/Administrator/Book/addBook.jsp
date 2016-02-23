<%@page import="com.gcit.training.spring.lms.entity.LibraryBranch"%>
<%@page
	import="com.gcit.training.spring.lms.service.AdminstratorProfile"%>
<%@page import="com.gcit.training.spring.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.spring.lms.entity.Publisher"%>
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
	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	AdminstratorProfile service = (AdminstratorProfile) ac.getBean("adminProfile");
%>

<body class="container-fluid">
	<form method="post" action="Administrator/AddBook">
		<div class="col-xs-6">
			
				<label for="ex3">Insert book title:</label> <input
					class="form-control " type=text name=bookTitle
					placeholder="Insert Title"><br />
		<br />

	<div class="form-group">
					<div class="col-xs-6">
				<label for="ex23"> Enter the number of copies:</label>
					</div><div class="col-xs-2">
				 <input
					type="text" class="form-control" name="amount"
					placeholder="#">
		</div></div>
					<br />		<br />
				<label for="ex11">select the book Genre:</label> 		<br /><select
					class="selectpicker" multiple="multiple" name="generIds">
					<%
						List<Genre> genres = service.getGenres(1);
						for (Genre g : genres) {
					%>
					<option value=<%out.print(g.getGenreId());%>>
						<%
							out.print(g.getGenreName());
						%>
					</option>
					<%
						}
					%>
				</select>
			<br /> <label for="ex11">select the book publisher:</label> 	<br /><select
				id="ex11" class="selectpicker" name="pubId">
				<%
					List<Publisher> publisher = service.getPublishers(1);
					for (Publisher p : publisher) {
				%>
				<option value=<%out.print(p.getPublisherId());%>>
					<%
						out.print(p.getPublisherName());
					%>
				</option>
				<%
					}
				%>
			</select> <br /> <br /> <br /> <br />

			<label for="e11">select the book Authors:</label>		<br />
			<select class="selectpicker" multiple="multiple" name="authorIds">
				<%
					List<Author> authors = service.getAuthorsSimpleData(1);
					for (Author a : authors) {
				%>
				<option value=<%out.print(a.getAuthorId());%>>
					<%
						out.print(a.getAuthorName());
					%>
				</option>
				<%
					}
				%>
			</select>


	<br />
			<label for="e11">select the for what branches:</label>	<br />
			<select class="selectpicker" multiple="multiple" name="branchIds">
				<%
					List<LibraryBranch> branches = service.getLibraries(1);
					for (LibraryBranch lb : branches) {
				%>
				<option value=<%out.print(lb.getBranchId());%>>
					<%
						out.print(lb.getBranchName());
					%>
				</option>
				<%
					}
				%>
			</select> <br />
		</div>
		<button class="btn btn-primary btn-lg" type=submit>Create Book</button>
		
	</form><br />
	<button onclick="javascript:location.href='Administrator/ViewBooks'" 
		class="btn btn btn-danger btn-lg">Cancel</button>
</body>

</html>