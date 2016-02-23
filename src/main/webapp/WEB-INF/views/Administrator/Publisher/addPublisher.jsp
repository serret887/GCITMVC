<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
	data-target="#myModal21">ADD Publisher</button>
<!-- Modal -->
<div class="modal fade " id="myModal21" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Insert Publisher </h4>
			</div>
			<form action="Administrator/AddPublisher" method="post">
				<div class="form-group">
					<label for="bname">Enter Name:</label> <input id="bname"
						type="text" class="form-control" placeholder="new Borrower Name"
						name="name"><br /> <label for="bphone">Enter
						Phone:</label> <input id="bphone" type="text" name="phone"
						placeholder="Enter Phone" class="form-control"> <label
						for="baddress"> Enter Address:</label> <input id="baddress"
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


