<%@ include file="../header.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link href="css/addBookStyle.css" rel="stylesheet" type="text/css">

<div class="container-fluid px-1 mx-auto">
	<div class="row d-flex justify-content-center">
		<div class="col-xl-10 col-lg-8 col-md-9 col-11 text-center">
			<div class="card" style="margin-top: 30px;
    margin-bottom: 30px;">
				<h5 class="text-center mb-4">
					<b>ADD MEMBER DETAILS</b>
				</h5>
				<form:form class="form-card" method="post" modelAttribute="member" enctype="multipart/form-data">
					<div class="row justify-content-between text-left my-4">
						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="memberId">Member Id<span
									class="text-danger"> *</span>
							</form:label>
							<form:input class="form-control" type="number" id="memberId" name="memberId" path="memberId"
								placeholder="Enter the Member Id" onblur="validate(1)"  />
							<form:errors path="memberId" cssClass="text-danger" ></form:errors>
							
						</div>
						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="firstName">First Name<span
									class="text-danger"> *</span>
							</form:label>
							<form:input type="text" id="isbn" name="isbn" path="firstName"
								placeholder="Enter the First Name" onblur="validate(2)" />
							<form:errors path="firstName" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<div class="row justify-content-between text-left my-4">
						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="lastName">Last Name<span
									class="text-danger"> *</span>
							</form:label>
							<form:input type="text" id="lastName" name="lastName"
								path="lastName" placeholder="Enter the Last Name"
								onblur="validate(3)" />
							<form:errors path="lastName" cssClass="text-danger"></form:errors>
						</div>
						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3"
								path="contactNumber">Contact Number<span
									class="text-danger"> *</span>
							</form:label>
							<form:input type="number" id="contactNumber"
								name="contactNumber" path="contactNumber"
								placeholder="Enter the Contact Number" onblur="validate(4)" />
							<form:errors path="contactNumber" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<div class="row justify-content-between text-left my-4">
						<div class="form-group col-sm-12 flex-column d-flex">
							<form:label class="form-control-label px-3" path="email">Email<span
									class="text-danger"> *</span>
							</form:label>
							<form:input type="email" id="email" name="email"
								path="email" placeholder="Enter the Email"
								onblur="validate(3)" />
							<form:errors path="email" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<div class="row justify-content-between mt-2">
						<div class="form-group col-sm-6">
							<button class="btn btn-primary rounded-pill px-3" type="submit">Submit</button>
						</div>
						<div class="form-group col-sm-6">
							<a href="/memberLists"><button class="btn btn-danger rounded-pill px-3" type="button">Cancel</button></a>
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