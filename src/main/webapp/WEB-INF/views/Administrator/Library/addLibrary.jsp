<button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
	data-target="#myModal1">ADD Library</button>
	
<!-- Modal -->
<div class="modal fade " id="myModal1" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Insert Library Data</h4>
			</div>
			
			<form action="Administrator/AddLibrary" method="post">
				<div class="form-group">
					<label for="bname">Enter Library  Name:</label> <input id="bname"
						type="text" class="form-control" placeholder="new Borrower Name"
						name="name">
						<br /> <label for="bphone">
						 <label
						for="baddress"> Enter Library Address:</label> <input id="baddress"
						type="text" class="form-control" name="address"
						placeholder="Enter address">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">ADD</button>
				</div>
			</form>
		</div>
	</div>
</div>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>