<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<button type="button" class="" data-toggle="modal"
	data-target="#myModal2">ADD</button>
<!-- Modal -->
<div class="modal fade " id="myModal2" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">ADD Author</h4>
			</div>
		
			<form action="Administrator/AddAuthor" method="post">
				<div class="modal-body">
				<div class="form-group">
				<div class="col-xs-6">
					<label for="bname">Enter Author Name:</label> <input id="bname"
						type="text" class="form-control" placeholder="new Borrower Name"
						name="name">
				</div>	</div><br /><br /><br />
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">ADD</button>
				</div>
			</form>
		</div>
	</div>
</div>
	<script>
		$(document).ready(function() {
			$('.selectpicker').selectpicker();
		});
	</script>
