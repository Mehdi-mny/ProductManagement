<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/5.3.3/css/bootstrap.min.css" />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Creation</title>
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
                        <a class="nav-link" href="productsList">Products List</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- Navbar -->

    <!-- Jumbotron -->
    <div class="p-1 text-center bg-light">
        <h1 class="mb-3">Product Creation - JSP</h1>
    </div>
    <!-- Jumbotron -->
</header>
<main class="container mt-5">
    <form action="saveProduct" method="post">
        <div>
            <label for="Name">Name : </label>
            <input type="text" id="Name" name="Name">
        </div>
        <div>
            <label for="Provider">Provider : </label>
            <input type="text" id="Provider" name="Provider">
        </div>
        <div>
            <label for="Category">Category : </label>
            <input type="text" id="Category" name="Category">
        </div>
        <div>
            <label for="unit_cost">Unit Cost : </label>
            <input type="number" id="unit_cost" name="unit_cost">
        </div>
        <div>
            <label for="unit_price">Unit Price : </label>
            <input type="number" id="unit_price" name="unit_price">
        </div>
        <div>
            <label for="image">Image : </label>
            <input type="image" id="image" name="image" alt="test">
        </div>
        <div>
            <input type="submit" value="Save">
        </div>
    </form>

</main>

<footer class="text-center text-lg-start bg-light text-muted">
    <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
        © 2023 Copyright:
        <a class="text-reset fw-bold" href="http://6solutions.com/">6solutions.com</a>
    </div>
</footer>
</body>
</html>

