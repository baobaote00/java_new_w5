<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Category Management</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="" class="navbar-brand">
					Category Management</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/category/list"
					class="nav-link">Categories</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Categories</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/category/new" class="btn btn-success">Add
					New User</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="category" items="${listCategory}">
						<tr>
							<td><c:out value="${category.id}" /></td>
							<td><c:out value="${category.name}" /></td>
							<td><a href="../category/edit?id=<c:out value='${category.id}' />">Edit</a>
								&nbsp;&nbsp;<a href="../category/delete?id=<c:out value='${category.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
