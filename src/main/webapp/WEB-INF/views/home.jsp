
<%@include file="./include.html"%>
  <style type="text/css">
.peopleCarouselImg img {
  width: auto;
  height: 225px;
  max-height: 225px;
}
  </style>
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active peopleCarouselImg">
    <center>  <img class="peopleCarouselImg" src="http://www.amenseminary.com/images/library_banner.jpg" alt="..."></center>
      <div class="carousel-caption">
        ...
      </div>
    </div>
    <div class="item peopleCarouselImg">
    <center><img  class="peopleCarouselImg" src="https://spu.edu/depts/english/images/banner.png" alt="..."></center>  
      <div class="carousel-caption">
        ...
      </div>
    </div>
      <div class="item peopleCarouselImg">
    <center><img  class="peopleCarouselImg" src="http://3.bp.blogspot.com/-JpsN-k-U-4A/UiF6O8xfH2I/AAAAAAAAE3g/oMQhMs0KMCI/s640/Banner-Library2.jpg" alt="..."></center>  
      <div class="carousel-caption">
        ...
      </div>
    </div>
    ...
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>










<div class="col-sm-4 col-lg-4 col-md-4">
	<div class="card thumbnail text-xs-center">
		<img class="card-img-top" onclick="javascript:location.href='Administrator'"
		src="http://4.bp.blogspot.com/-iyqJp-GcFqw/Uok6z4y90LI/AAAAAAAAF3U/cdKTQoVUA9w/s1600/99255c3f1b1511a84319bd4130f155e5.jpg" alt="Card image cap">
		<div class="card-block">
			<h4 class="card-title  text-center">ADMINISTRATOR</h4>
			<p class="card-text  text-center">The administrator profile can execute all
				CRUD operation over the Books, Author, Genres, Publisher's and
				Library of the Library Manage System HI IS GOD!!!!</p>
			<center>
				<button onclick="javascript:location.href='Administrator'" href="Administrator" class="btn btn-primary btn-lg">Click
					here if you are god</button>
			</center>
		</div>
	</div>

</div>
<div class="col-sm-4 col-lg-4 col-md-4">
	<div class="card thumbnail text-xs-center">
		<img class="card-img-top img-porta img-rounded img-responsive" onclick="javascript:location.href='Librarian'"
			src="https://s-media-cache-ak0.pinimg.com/236x/3c/38/3d/3c383d2ef0fee103a96ce51783f18f82.jpg" alt="Card image cap">
		<div class="card-block">
			<h4 class="card-title text-center">LIBRARIAN</h4>
			<p class="card-text text-center">This is the librarian Profile
				the librarian can add books copies to his library and edit any 
				library name and address                  </p><br />
			<center>
				<button onclick="javascript:location.href='Librarian'" class="btn btn-lg btn-primary ">Librarian</button>
			</center>
		</div>
	</div>
</div>

<div class="col-sm-4 col-lg-4 col-md-4">
	<div class="card thumbnail text-xs-center">
		<img class="card-img-top" src="https://s-media-cache-ak0.pinimg.com/236x/0c/90/e8/0c90e8a42a08fa4243325b297bd1df7f.jpg"
			alt="Card image cap">
		<div class="card-block">
			<h4 class="card-title  text-center">User</h4>
			<p class="card-text  text-center">The User can checkOut and CheckIn Books
				from an Specific Branch and also return and see all teh loans that her already have</p>
			<!-- Button trigger modal -->
			<center>
				<button type="button " class="btn btn-primary btn-lg"
					data-toggle="modal" data-target="#myModal">User</button>
				<center>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Insert Card Number</h4>
			</div>	
			<div class="modal-body">
			<form id="cardValidation" action="Borrower/CheckValidCard"
				method="post">
			
						<label>Card Number</label> <input type="password"
							class="form-control col-xs-4" name="cardNo" placeholder="Card Number">
					<br /><br /><br />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Enter</button>
				</div>
			</form>
		</div>
	</div>
</div>

