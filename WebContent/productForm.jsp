<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Product</title>
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
				<li>
					<a href="<%=request.getContextPath()%>/product/list" class="nav-link">Products</a>
				</li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<c:if test="${product != null}">
					<form action="${pageContext.request.contextPath}/product/update" method="post" enctype="multipart/form-data">
				</c:if>

				<c:if test="${product == null}">
					<form action="${pageContext.request.contextPath}/product/insert" method="post" enctype="multipart/form-data">
				</c:if>

				<caption>
					<h2>
						<c:if test="${product != null}">
            			Edit Product
            			</c:if>

						<c:if test="${product == null}">
            			Add New Product
            			</c:if>
					</h2>
				</caption>

				<c:if test="${product != null}">
					<input type="hidden" name="id" value="<c:out value='${product.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label for="name">Product name</label> 
					<input type="text" value="<c:out value='${product.productName}' />" class="form-control" id="name" name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label for="price">Product price</label> 
					<input type="number" value="<c:out value='${product.productPrice}' />" class="form-control" id="price" name="price" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label for="image">Product image:</label>
					<input type="file" id="image" name="image" />
				</fieldset>
				
				<fieldset class="form-group">
					<label for="description">Product description:</label> 
					<textarea name="description" id="description" rows="5" ></textarea>
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>