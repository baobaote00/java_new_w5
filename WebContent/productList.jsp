<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="" class="navbar-brand">Product Management</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/product/list" class="nav-link">Products</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Products</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/product/new" class="btn btn-success">Add New User</a>
			</div>
			<br>	
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Price</th>
						<th style="width: 100px;">Image</th>
						<th>Description</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${listProduct}">
						<tr>
							<td><c:out value="${product.id}" /></td>
							<td><c:out value="${product.productName}" /></td>
							<td><c:out value="${product.productPrice}" /></td>
							<td><img src="data:image/jpg;base64,${product.image}" class="img-fluid"></td>
							<td><c:out value="${product.description}" /></td>
							<td>
								<a href="<%=request.getContextPath()%>/product/edit?id=<c:out value='${product.id}' />">Edit</a>&nbsp;&nbsp;
								<a href="<%=request.getContextPath()%>/product/delete?id=<c:out value='${product.id}' />">Delete</a>
								<a href="<%=request.getContextPath()%>/product/add?id=<c:out value='${product.id}' />">Add</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>