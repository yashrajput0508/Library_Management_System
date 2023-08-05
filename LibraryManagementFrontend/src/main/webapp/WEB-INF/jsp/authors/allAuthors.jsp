<%@ include file="../header.jspf"%>

<div class="main-content">
	<div class="container mt-3">
		<!-- Table -->
		<!-- <h2 class="mb-5">Tables Example</h2> -->
		<div class="row">

			<div class="col">
				<div class="card shadow">
					<div class="card-header border-0">
						<h2 class="mb-0 text-center">Author Lists</h2>
					</div>
					<div class="table-responsive">
						<table class="table align-items-center table-flush">
							<thead class="thead-light">
								<tr>
									<th scope="col">Author Id</th>
									<th scope="col">Author Name</th>
									<th scope="col">Author Description</th>
									<th scope="col">Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="author" items="${paginationResult.items}">
									<tr>
										<th scope="row"><c:out value="${author.authorId}" /></th>
										<td><c:out value="${author.authorName}" /></td>
										<td><c:out value="${author.description}" /></td>
										<td>
											<div class="dropdown">
												<div>
													<a href="/editauthor?authorId=${author.authorId}"
														class="btn btn-success btn-sm rounded-0" type="button"
														data-toggle="tooltip" data-placement="top" title="Edit">
														<i class="fa fa-edit"></i>
													</a> <a href="/deleteauthor?authorId=${author.authorId}"
														class="btn btn-danger btn-sm rounded-0" type="button"
														data-toggle="tooltip" data-placement="top" title="Delete"
														onclick="return confirmDelete()"> <i
														class="fa fa-trash"></i>
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

						<a href="/addauthor" class="btn btn-success rounded-pill px-3"
							type="button">Add Author</a>
						<nav aria-label="...">

							<ul class="pagination justify-content-end mb-0">
								<c:if test="${paginationResult.currentPage > 1}">
									<li class="page-item"><a class="page-link"
										href="?page=${paginationResult.currentPage - 1}" tabindex="-1">
											<span aria-hidden="true">&laquo;</span> <span class="sr-only">Previous</span>
									</a></li>
								</c:if>

								<c:forEach begin="1"
									end="${paginationResult.totalItems / paginationResult.pageSize+1}"
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
		return confirm("Are you sure you want to delete this author data?");
	}
</script>

<%
// Set the parameters as request attributes
request.setAttribute("footer", "fixed-bottom");
// ...
%>

<%@ include file="../footer.jspf"%>