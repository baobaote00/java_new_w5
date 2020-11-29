<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Category Management</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="" class="navbar-brand"> Category Management </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/category/list"
					class="nav-link">Categories</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<c:if test="${category != null}">
					<form action="../category/update" method="post">
				</c:if>

				<c:if test="${category == null}">
					<form action="category/insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${category != null}">
            			Edit Category
            		</c:if>

						<c:if test="${category == null}">
            			Add New Category
            		</c:if>

					</h2>
				</caption>

				<c:if test="${category != null}">
					<input type="hidden" name="id"
						value="<c:out value='${category.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Name</label> <input type="text"
						value="<c:out value='${category.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>