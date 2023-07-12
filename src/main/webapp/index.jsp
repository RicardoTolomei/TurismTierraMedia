<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="partials/head.jsp"></jsp:include>


</head>
<body class="container">
<jsp:include page="partials/nav.jsp"></jsp:include>
	<main class="container">
		<div class="bg-light p-4 rounded">
			<h1>
				¡Bienvenido, <c:out value="${user.usuario}" />!
			</h1>
		</div>
	</main>
	
	
	<!-- <img src="res/20171231215950_parque_lanin.jpg" class="img-fluid" alt="..."> -->
	
	<h3>Ofrecemos Los siguientes tipos de atracciones</h3>
	<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="assets/img/Mordor.jpg" class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
        <h5>AVENTURA</h5>
        <p></p>
      </div>
       
    </div>
    <div class="carousel-item">
      <img src="assets/img/edoras.jpg" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
        <h5>DEGUSTACION</h5>
        <p></p>
      </div>
       
    </div>
    <div class="carousel-item">
      <img src="assets/img/argonath.jpg" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
        <h5>PAISAJE</h5>
        <p></p>
      </div>
       
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
	
</body>
</html>
