<%@ include file="../header.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link href="css/addBookStyle.css" rel="stylesheet" type="text/css">

<div class="container-fluid px-1 mx-auto">
	<div class="row d-flex justify-content-center">
		<div class="col-xl-10 col-lg-8 col-md-9 col-11 text-center">
			<div class="card" style="margin-top: 30px;
    margin-bottom: 30px;">
				<h5 class="text-center mb-4">
					<b>EDIT BOOK DETAILS</b>
				</h5>
				<form:form class="form-card" method="post" modelAttribute="book" enctype="multipart/form-data">
					<div class="row justify-content-between text-left my-4">
						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="bookId">Book Id<span
									class="text-danger"> *</span>
							</form:label>
							<form:input class="form-control" type="number" id="bookId" name="bookId" path="bookId"
								placeholder="Enter the Book Id" onblur="validate(1)" disabled="true" />
							<form:errors path="bookId" cssClass="text-danger"></form:errors>
							
						</div>
						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="ISBN">ISBN<span
									class="text-danger"> *</span>
							</form:label>
							<form:input type="text" id="isbn" name="isbn" path="ISBN"
								placeholder="Enter the ISBN No." onblur="validate(2)" />
							<form:errors path="ISBN" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<div class="row justify-content-between text-left my-4">
						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="bookName">Book Name<span
									class="text-danger"> *</span>
							</form:label>
							<form:input type="text" id="bookName" name="bookName"
								path="bookName" placeholder="Enter the Book Name"
								onblur="validate(3)" />
							<form:errors path="bookName" cssClass="text-danger"></form:errors>
						</div>
						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3"
								path="publicationYear">Publication Year<span
									class="text-danger"> *</span>
							</form:label>
							<form:input type="number" id="publicationYear"
								name="publicationYear" path="publicationYear"
								placeholder="Enter the Publication Year" onblur="validate(4)" />
							<form:errors path="publicationYear" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<div class="row justify-content-between text-left my-4">

						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="image">Image<span
									class="text-danger"> *</span>
							</form:label>
							<form:input type="file" path="image" accept="image/*" />
							<form:errors path="image" cssClass="text-danger"></form:errors>
						</div>

						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="author">Author<span
									class="text-danger"> *</span>
							</form:label>
							<form:select class="form-select mt-2 py-2"
								aria-label="Default select example" path="author.authorId">
									<form:options items="${authors}" itemValue="authorId" itemLabel="authorName" />
							</form:select>
							
							<form:errors path="author" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<div class="row justify-content-between text-left mb-4">
						<div class="form-group col-sm-6 flex-column d-flex mt-3">
							<div class="form-check">
								<form:checkbox class="form-check-input" path="availability"
									id="flexCheckDefault" />
								<form:label class="form-check-label" for="flexCheckDefault"
									path="availability">Is Book Available in the Library</form:label>
							</div>
						</div>
					</div>
					<div class="row justify-content-between mt-2">
						<div class="form-group col-sm-6">
							<button class="btn btn-primary rounded-pill px-3" type="submit">Submit</button>
						</div>
						<div class="form-group col-sm-6">
							<a href="/bookLists"><button class="btn btn-danger rounded-pill px-3" type="button">Cancel</button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<%@ include file="../footer.jspf"%>