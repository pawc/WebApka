<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>WebApka</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans|Dosis:400,800" rel="stylesheet" type="text/css" />



</head>

<body>

<div class="plane">
</div>
<div class="cloud">
</div>

<div id="logowanie" class="wrapper">
	<div class="header sm-col-12">
			<h2>Logowanie</h2>
	</div>
	<form class="form-horizontal" action="Login" method="post">
		<div class="row">
			<div class="col-sm-12 input">
				<input type="text" class="form-control" name="name" placeholder="Imię">
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12 input">
				<input type="password" class="form-control" name="password" placeholder="Hasło">
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<button type="submit" class="btn btn-custom">Zaloguj się</button>
			</div>
		</div>
	</form>
	<div class="footer sm-col-12">
		<a id="a-rejestr" href="">Zarejestruj się</a>
	</div>
</div>

<div id="rejestracja" class="wrapper">
	<div class="header sm-col-12">
			<h2>Rejestracja</h2>
	</div>
	<form class="form-horizontal" action="Register" method="post">
		<div class="row">
			<div class="col-sm-12 input">
				<input type="text" class="form-control" name="name" placeholder="Imię">
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12 input">
				<input type="password" class="form-control" name="password" placeholder="Hasło">
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<button type="submit" class="btn btn-custom">Załóż konto</button>
			</div>
		</div>
	</form>
	<div class="footer sm-col-12">
		<a id="a-loguj" href="">Zaloguj się</a>
	</div>
</div>

    <script src="js/jquery.js"></script>
	<script src="js/javascript.js"></script>
</body>
</html>
