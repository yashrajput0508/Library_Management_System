<%@ include file="../header.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link href="css/addBookStyle.css" rel="stylesheet" type="text/css">

<div class="container-fluid px-1 mx-auto">
	<div class="row d-flex justify-content-center">
		<div class="col-xl-10 col-lg-8 col-md-9 col-11 text-center">
			<div class="card" style="margin-top: 30px;
    margin-bottom: 30px;">
				<h5 class="text-center mb-4">
					<b>ADD AUTHOR DETAILS</b>
				</h5>
				<form:form class="form-card" method="post" modelAttribute="author" enctype="multipart/form-data">
					<div class="row justify-content-between text-left my-4">
						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="authorId">Author Id<span
									class="text-danger"> *</span>
							</form:label>
							<form:input class="form-control" type="number" id="authorId" name="authorId" path="authorId"
								placeholder="Enter the Author Id" onblur="validate(1)" />
							<form:errors path="authorId" cssClass="text-danger"></form:errors>
							
						</div>
						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="authorName">Author Name<span
									class="text-danger"> *</span>
							</form:label>
							<form:input type="text" id="authorName" name="authorName" path="authorName"
								placeholder="Enter the Author Name" onblur="validate(2)" />
							<form:errors path="authorName" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<div class="row justify-content-between text-left my-4">
						<div class="form-group col-sm-12 flex-column d-flex">
							<form:label class="form-control-label px-3" path="description">Description<span
									class="text-danger"> *</span>
							</form:label>
							<form:textarea  type="text" id="bookName" name="description"
								path="description" placeholder="Enter the Description"
								onblur="validate(3)" />
							<form:errors path="description" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<div class="row justify-content-between mt-2">
						<div class="form-group col-sm-6">
							<button class="btn btn-primary rounded-pill px-3" type="submit">Submit</button>
						</div>
						<div class="form-group col-sm-6">
							<a href="/authorLists"><button class="btn btn-danger rounded-pill px-3" type="button">Cancel</button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<!-- Custom error alert -->
<c:if test="${not empty customError}">
    <script>
        alert("${customError}");
    </script>
</c:if>


<%@ include file="../footer.jspf"%>