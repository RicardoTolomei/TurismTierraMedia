<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">

	<div class="mb-3">
		<label for="nombre" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="name" name="nombre" required
			value="${attraction.nombre}">
	</div>

	<div class="mb-3">
		<label for="costo"
			class='col-form-label ${attraction.errors.get("costo") != null ? "is-invalid" : "" }'>Costo:</label>
		<input class="form-control" type="number" id="cost" name="costo"
			required value="${attraction.costo}"></input>
		<div class="invalid-feedback">
			<c:out value='${attraction.errors.get("cost")}'></c:out>
		</div>
	</div>

	<div class="mb-3">
		<label for="duracion"
			class='col-form-label ${attraction.errors.get("duracion") != null ? "is-invalid" : "" }'>Duracion:</label>
		<input class="form-control" type="number" id="duration"
			name="duracion" required value="${attraction.duracion}"></input>
		<div class="invalid-feedback">
			<c:out value='${attraction.errors.get("duracion")}'></c:out>
		</div>
	</div>

	<div class="mb-3">
		<label for="cupoActual"
			class='col-form-label ${attraction.errors.get("cupoActual") != null ? "is-invalid" : "" }'>CupoActual:</label>
		<input class="form-control" type="number" id="capacity"
			name="cupoActual" required value="${attraction.cupoActual}"></input>
		<div class="invalid-feedback">
			<c:out value='${attraction.errors.get("cupoActual")}'></c:out>
		</div>
		<label for="cupoMaximo"
			class='col-form-label ${attraction.errors.get("cupoMaximo") != null ? "is-invalid" : "" }'>CupoMaximo:</label>
		<input class="form-control" type="number" id="capacity"
			name="cupoMaximo" required value="${attraction.cupoMaximo}"></input>
		<div class="invalid-feedback">
			<c:out value='${attraction.errors.get("cupoMaximo")}'></c:out>
		</div>
	</div>

	<div class="mb-3">
		<label for="capacity"
			class='col-form-label ${attraction.errors.get("posicionX") != null ? "is-invalid" : "" }'>PosicionX:</label>
		<input class="form-control" type="number" id="capacity"
			name="posicionX" required value="${attraction.posicionX}"></input>
		<div class="invalid-feedback">
			<c:out value='${attraction.errors.get("posicionX")}'></c:out>
		</div>
	</div>

	<div class="mb-3">
		<label for="capacity"
			class='col-form-label ${attraction.errors.get("PosicionY") != null ? "is-invalid" : "" }'>PosicionY:</label>
		<input class="form-control" type="number" id="capacity"
			name="posicionY" required value="${attraction.posicionY}"></input>
		<div class="invalid-feedback">
			<c:out value='${attraction.errors.get("posicionY")}'></c:out>
		</div>
	</div>

	<div class="mb-3">
		<label for="preferencias" class="col-form-label">Preferencias:
			(Solo valido (" PAISAJE ")|| (" AVENTURA ") || (" DEGUSTACION "))</label> <input
			type="text" class="form-control" id="name" name="preferencias"
			required value="${attraction.nombre}">
	</div>

</div>

<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
		<span>Se debe colocar "0" en caso de que corresponda</span>
</div>
