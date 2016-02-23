<%@include file="./include.html"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
</head>
<h1>Hello Administrator</h1>
<br />
<h2>What are your commands</h2>

  <style type="text/css">
.imgP img {
  width: auto;
  
  height: 294px;
  max-height: 294px;
}
  </style>
<br />
<body class="container">
	<div>
		<div class="col-sm-6 col-lg-6 col-md-6">
			<div class="card thumbnail text-xs-center">
				<img class="imgP card-img-top"
					src="https://s-media-cache-ak0.pinimg.com/236x/26/44/1b/26441b5493e16419505d1cfb61296103.jpg"
					alt="Card image cap">
				<div class="card-block">
					<h4 class="card-title  text-center">BOOKS</h4>
					<p class="card-text  text-center">With this option the
						administrator is able of view the full list of books add new books
						and copies to the branches and edit it</p>
					<center>
						<button
							onclick="javascript:location.href='Administrator/ViewBooks'"
							class="btn btn-primary btn-lg">Admin Books</button>
					</center>
				</div>
			</div>
		</div>
		<div class="col-sm-6 col-lg-6 col-md-6">
			<div class="card thumbnail text-xs-center">
				<img class="imgP card-img-top"
					src="http://m5.paperblog.com/i/6/69916/review-love-loss-and-what-i-wore-broadway-in--L-ohZRxj.jpeg"
					alt="Card image cap">
				<div class="card-block">
					<h4 class="card-title  text-center">AUTORS</h4>
					<p class="card-text  text-center">A good Administrator is able
						to edit, add and delete everything and Autors are not the
						exception</p>
					<center>
						<button
							onclick="javascript:location.href='Administrator/ViewAuthors'"
							class="btn btn-primary btn-lg">Admin Authors</button>
					</center>
				</div>
			</div>
		</div>
	</div>
	<div>

		<div class="col-sm-6 col-lg-6 col-md-6">
			<div class="card thumbnail text-xs-center">
				<img class="card-img-top"
					src="https://s-media-cache-ak0.pinimg.com/236x/67/fd/dd/67fddd3b81e0009b40b0adcc4f00ce38.jpg"
					alt="Card image cap">
				<div class="card-block">
					<h4 class="card-title  text-center">PUBLISHERS</h4>
					<p class="card-text  text-center">Here The Administrator can
						change, add and delete publisher. But you need to remember every
						time you delete a publisher it delete all the books asociate to it
						too</p>
					<center>
						<button
							onclick="javascript:location.href='Administrator/ViewPublishers?pageNo=1'"
							class="btn btn-primary btn-lg">Admin Publisher</button>
					</center>
				</div>
			</div>
		</div>
		<div class="col-sm-6 col-lg-6 col-md-6">
			<div class="card thumbnail text-xs-center">
				<img class="card-img-top"
					src="https://s-media-cache-ak0.pinimg.com/236x/76/fb/53/76fb53e05672cafd88e83bcfafdfd7af.jpg"
					alt="Card image cap">
				<div class="card-block">
					<h4 class="card-title  text-center">LIBRARY</h4>
					<p class="card-text  text-center">Here The Administrator can
						change, add and delete library. But you need to remember every
						time you delete a publisher it delete all the books asociate to it
						too</p>
					<center>
						<button
							onclick="javascript:location.href='Administrator/ViewLibraries?pageNo=1'"
							class="btn btn-primary btn-lg">Admin Libraries</button>
					</center>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="col-sm-6 col-lg-6 col-md-6">
			<div class="card thumbnail text-xs-center">
				<img class="card-img-top"
					src="https://s-media-cache-ak0.pinimg.com/236x/a6/86/62/a68662a2c8dd33c7b7baef5711923d63.jpg"
					alt="Card image cap">
				<div class="card-block">
					<h4 class="card-title  text-center">BORROWER</h4>
					<p class="card-text  text-center">Here The Administrator Add
						Update Borrower's ups Users</p>
					<center>
						<button onclick="javascript:location.href='Administrator/ViewBorrowers?pageNo=1'"
							class="btn btn-primary btn-lg">Admin Users</button>
					</center>
				</div>
			</div>
		</div>
	</div>
</body>
</html>