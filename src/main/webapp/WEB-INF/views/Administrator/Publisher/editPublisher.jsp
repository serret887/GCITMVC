<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page
	import="com.gcit.training.spring.lms.service.AdminstratorProfile"%>
<%@page import="com.gcit.training.spring.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.spring.lms.entity.Publisher"%>
<%@page import="com.gcit.training.spring.lms.entity.Genre"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<html>
<head>
<meta charset="ISO-8859-1">
</head>

<%
	Integer pubId = Integer.parseInt(request.getParameter("pubId"));
	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	AdminstratorProfile service = (AdminstratorProfile) ac.getBean("adminProfile");
	Publisher publisher = service.getPublisherById(pubId);
%>
<body>

</body>
<div class="modal-header">
	<h2>Enter Publisher Details to Edit:</h2>
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">Insert Card Number</h4>
</div>
<div class=" modal-body">
		<form action="Administrator/EditPublisher" method="post">
			<div class="form-group">
				<label for="bname">Enter Publisher Name:</label> <input id="bname"
					type="text" class="form-control"
					value="<%=publisher.getPublisherName()%>" name="pubName"><br />
				<input type="hidden" name="pubId" value="<%=pubId%>" /> <label
					for="baddress"> Enter Updated Publisher Address: </label><input
					class="form-control" type="text" name="pubAddress"
					value=<%=publisher.getPublisherAddress()%>><br />
					<div class="col-sm-6 col-lg-6 col-md-6">
					 <label
					for="bphone"> Enter Updated Publisher Phone: </label><input
					type="text" class="form-control" name="pubPhone"
					value=<%=publisher.getPublisherPhone()%>>
			</div></div>

	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	<button type="submit" class="btn btn-primary">EDIT</button><br /><br /><br />
	</form>
</div>
</html>
