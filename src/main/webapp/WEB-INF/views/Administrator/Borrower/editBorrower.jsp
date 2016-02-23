<%@page import="com.gcit.training.spring.lms.service.BorrowerProfiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page
	import="com.gcit.training.spring.lms.service.AdminstratorProfile"%>
<%@page import="com.gcit.training.spring.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.spring.lms.entity.LibraryBranch"%>
<%@page import="com.gcit.training.spring.lms.entity.Borrower"%>
<%@page import="com.gcit.training.spring.lms.entity.Author"%>
<%@page import="com.gcit.training.spring.lms.entity.Publisher"%>

<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<%
	Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	BorrowerProfiles service = (BorrowerProfiles) ac.getBean("borrowerProfile");
	Borrower borrower = service.getBorrowerById(cardNo);
%>

<body>
<form action="Administrator/EditBorrower" method="post">
	<br />
	<input type="hidden" name="cardNo" class="form-control" value=<%=borrower.getCarNo()%>>
	Enter Updated Borrower Name:
	<input type="text" name="name" value=<%=borrower.getName()%> class="form-control">
	 
	 Enter Updated 	Borrower Address:
	<input type="text" name="address" value=<%=borrower.getAddress()%> class="form-control">
	<p>
		Enter Updated Borrower Phone:
		 <input type="text" name="phone" class="form-control" value=<%=borrower.getPhone()%>>
	</p>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		<button type="submit" class="btn btn-primary">EDIT</button>
	</div>
	</form>
</body>
