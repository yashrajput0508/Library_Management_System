<%@ include file="../header.jspf"%>

<div class="main-content">
	<div class="container mt-3">
		<!-- Table -->
		<!-- <h2 class="mb-5">Tables Example</h2> -->
		<div class="row">

			<div class="col">
				<div class="card shadow">
					<div class="card-header border-0">
						<h2 class="mb-0 text-center">Member Lists</h2>
					</div>
					<div class="table-responsive">
						<table class="table align-items-center table-flush">
							<thead class="thead-light">
								<tr>
									<th scope="col">Member Id</th>
									<th scope="col">First Name</th>
									<th scope="col">Last Name</th>
									<th scope="col">Email</th>
									<th scope="col">Contact Number</th>
									<th scope="col">Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="member" items="${paginationResult.items}">
									<tr>
										<th scope="row">
											<c:out value="${member.memberId}" />
										</th>
										<td><c:out value="${member.firstName}" /></td>
										<td><c:out value="${member.lastName}" /></td>
										<td><c:out value="${member.email}" /></td>
										<td><c:out value="${member.contactNumber}" /></td>
										<td>
											<div class="dropdown">
												<div>
													<a href="/editmember?memberId=${member.memberId}" class="btn btn-success btn-sm rounded-0"
														type="button" data-toggle="tooltip" data-placement="top"
														title="Edit">
														<i class="fa fa-edit"></i>
													</a>
													<a href="/deletemember?memberId=${member.memberId}" class="btn btn-danger btn-sm rounded-0"
														type="button" data-toggle="tooltip" data-placement="top"
														title="Delete" onclick="return confirmDelete()">
														<i class="fa fa-trash"></i>
													</a>
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
					<div
						class="d-flex flex-wrap justify-content-between card-footer py-4">

						<a href="/addmember" class="btn btn-success rounded-pill px-3" type="button">Add
							Member</a>
						<nav aria-label="...">

							<ul class="pagination justify-content-end mb-0">
								<c:if test="${paginationResult.currentPage > 1}">
									<li class="page-item"><a class="page-link"
										href="?page=${paginationResult.currentPage - 1}" tabindex="-1">
											<span aria-hidden="true">&laquo;</span> <span class="sr-only">Previous</span>
									</a></li>
								</c:if>

								<c:forEach begin="1"
									end="${paginationResult.totalItems / paginationResult.pageSize + 1}"
									var="page">
									<c:if test="${paginationResult.currentPage == page}">
										<li class="page-item active"><a class="page-link"
											href="#">${page} <span class="sr-only">(current)</span></a></li>
									</c:if>
									<c:if test="${paginationResult.currentPage != page}">
										<li class="page-item"><a class="page-link"
											href="?page=${page}">${page}</a></li>
									</c:if>
								</c:forEach>

								<c:if
									test="${paginationResult.currentPage < (paginationResult.totalItems / paginationResult.pageSize)}">
									<li class="page-item"><a class="page-link"
										href="?page=${paginationResult.currentPage + 1}"> <span
											aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span>
									</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<!-- Dark table -->
	</div>
</div>

<script>
    function confirmDelete() {
        return confirm("Are you sure you want to delete this member?");
    }
</script>

<%
// Set the parameters as request attributes
request.setAttribute("footer", "fixed-bottom");
// ...
%>
<%@ include file="../footer.jspf"%>