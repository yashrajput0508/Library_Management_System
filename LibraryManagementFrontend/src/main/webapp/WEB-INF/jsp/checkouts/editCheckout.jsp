<%@ include file="../header.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link href="css/addBookStyle.css" rel="stylesheet" type="text/css">

<div class="container-fluid px-1 mx-auto">
	<div class="row d-flex justify-content-center">
		<div class="col-xl-10 col-lg-8 col-md-9 col-11 text-center">
			<div class="card" style="margin-top: 30px; margin-bottom: 30px;">
				<h5 class="text-center mb-4">
					<b>EDIT CHECKOUT DETAILS</b>
				</h5>
				<form:form class="form-card" method="post" modelAttribute="checkout"
					enctype="multipart/form-data">
					<div class="row justify-content-between text-left my-4">
						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="checkoutId">Checkout Id<span
									class="text-danger"> *</span>
							</form:label>
							<form:input class="form-control" type="number" id="checkoutId"
								name="checkoutId" path="checkoutId"
								placeholder="Enter the Checkout Id" onblur="validate(1)" />
							<form:errors path="checkoutId" cssClass="text-danger"></form:errors>

						</div>
						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="book">Book<span
									class="text-danger"> *</span>
							</form:label>

							<form:select class="form-select mt-2 py-2"
								aria-label="Default select example" path="book.bookId">
								<form:options items="${books}" itemValue="bookId"
									itemLabel="bookName" />
							</form:select>

							<form:errors path="book" cssClass="text-danger"></form:errors>
						</div>
					</div>
					
					<div class="row justify-content-between text-left my-4">
						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="member">Member<span
									class="text-danger"> *</span>
							</form:label>
							<form:select class="form-select mt-2 py-2"
								aria-label="Default select example" path="member.memberId">
								<c:forEach items="${members}" var="m">
									<c:choose>
										<c:when test="${m.memberId == checkout.member.memberId}">
											<option value="${m.memberId}" selected>${m.firstName}
												${m.lastName}</option>
										</c:when>
										<c:otherwise>
											<option value="${m.memberId}">${m.firstName}
												${m.lastName}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select>

							<form:errors path="member" cssClass="text-danger"></form:errors>
						</div>
						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="checkoutDate">Checkout Date<span
									class="text-danger"> *</span>
							</form:label>
							<form:input type="date" id="checkoutDate" name="checkoutDate"
								path="checkoutDate" onblur="validate(4)" />
							<form:errors path="checkoutDate" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<div class="row justify-content-between text-left my-4">

						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="dueDate">Due Date<span
									class="text-danger"> *</span>
							</form:label>
							<form:input type="date" id="dueDate" name="dueDate"
								path="dueDate" onblur="validate(4)" />
							<form:errors path="dueDate" cssClass="text-danger"></form:errors>
						</div>

						<div class="form-group col-sm-6 flex-column d-flex">
							<form:label class="form-control-label px-3" path="returnDate">Return Date<span
									class="text-danger"> *</span>
							</form:label>
							<form:input type="date" id="returnDate" name="returnDate"
								path="returnDate" onblur="validate(4)" />
							<form:errors path="returnDate" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<div class="row justify-content-between mt-2">
						<div class="form-group col-sm-6">
							<button class="btn btn-primary rounded-pill px-3" type="submit">Submit</button>
						</div>
						<div class="form-group col-sm-6">
							<a href="/checkoutLists"><button
									class="btn btn-danger rounded-pill px-3" type="button">Cancel</button></a>
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