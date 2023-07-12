<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Promociones</title>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>


<body class="container">
	<main class="container">
		<jsp:include page="/partials/nav.jsp"></jsp:include>
		<div class="bg-light p-4 mb-3 rounded">
			<h1>Estas son las promociones de la Tierra Media</h1>
		</div>

		<c:if test="${user.isAdministrador()}">
			<div class="mb-3">
				<a href="/TP003/promociones/create.do" class="btn btn-primary"
					role="button"> <i class="bi bi-plus-lg"></i> Nueva Promocion
				</a>
			</div>
		</c:if>

		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Tipo</th>
					<th>Costo</th>
					<th>Duracion</th>
					<th>descuentoPorcentual</th>
					<th>AtraccionA</th>
					<th>AtraccionB</th>
					<th>Distancia</th>
					<th>Acciones</th>
				</tr>
			</thead>




			<tbody>
				<c:forEach items="${promociones}" var="promocion">
					<tr>
						<td><strong><c:out value="${promocion.nombre}"></c:out></strong>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								Cras pretium eros urna. Sed quis erat congue, bibendum tortor
								malesuada, iaculis diam. Ut ut imperdiet sapien.</p></td>
						<td><c:out value="${promocion.tipoDePromocion}"></c:out></td>
						<td><c:out value="${promocion.costo}"></c:out></td>
						<td><c:out value="${promocion.getDuracion()}"></c:out></td>
						<td><c:out value="${promocion.descuentoPorcentual}"></c:out></td>
						<td><c:out value="${promocion.atracciones[0]}"></c:out></td>
						<td><c:out value="${promocion.atracciones[1]}"></c:out></td>
						<td><c:out value="${user.distance(promocion)}"></c:out></td>
						<td><c:if test="${user.administrador}">
								<a href="/TP003/promociones/edit.do?id=${promocion.id}"
									class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill"></i></a>
								<a href="/TP003/promociones/delete.do?id=${promocion.id}"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
							</c:if>
						<td><c:choose>
								<c:when
									test="${user.puedeComprarPromocion(promocion) && user.tieneTiempo(promocion)}">
									<a href="/TP003/promociones/buy.do?id=${promocion.id}"
										class="btn btn-success rounded" role="button">Comprar</a>
								</c:when>
								<c:otherwise>
									<a href="#" class="btn btn-secondary rounded disabled"
										role="button">No se puede comprar</a>

								</c:otherwise>
							</c:choose></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</main>
</body>
</html>