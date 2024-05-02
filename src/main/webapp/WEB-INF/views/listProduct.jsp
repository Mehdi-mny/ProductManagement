<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/5.3.3/css/bootstrap.min.css" />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products List</title>
</head>
<body>
<header>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-white">
        <div class="container-fluid">
            <button
                    class="navbar-toggler"
                    type="button"
                    data-mdb-toggle="collapse"
                    data-mdb-target="#navbarExample01"
                    aria-controls="navbarExample01"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
            >
                <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarExample01">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item active">
                        <a class="nav-link" href="createProduct">Create Customer</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- Navbar -->

    <!-- Jumbotron -->
    <div class="p-1 text-center bg-light">
        <h1 class="mb-3">Products List - JSP</h1>
    </div>
    <!-- Jumbotron -->
</header>
<main class="container mt-5">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">NAME</th>
                <th scope="col">QUANTITY</th>
                <th scope="col">CATEGORY</th>
                <th scope="col">PROVIDER</th>
                <th scope="col">COST PRICE</th>
                <th scope="col">UNIT PRICE</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${productvue}" var="product">
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.quantity}</td>
                        <td>${product.category}</td>
                        <td>${product.provider}</td>
                        <td>${product.unit_cost}</td>
                        <td>${product.unit_price}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
</main>

<footer class="text-center text-lg-start bg-light text-muted">
    <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
        © 2023 Copyright:
        <a class="text-reset fw-bold" href="http://6solutions.com/">6solutions.com</a>
    </div>
</footer>
</body>
</html>

