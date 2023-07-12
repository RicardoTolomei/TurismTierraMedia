<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">

	<div class="mb-3">
		<label for="nombre" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="name" name="nombre" required
			value="${promocion.nombre}">
	</div>

	<div class="mb-3">
		<label for="tipoDePromocion"
			class='col-form-label '>TipoDePromocion:
			(Valores: (1: Prom. Absoluta) (2: Prom. Procentual) (1: Prom. AxB))</label> <input
			class="form-control" type="number" id="tipodepromocion"
			name="tipodepromocion" required value="${promocion.tipoDePromocion}"></input>
		
		</div>

		<div class="mb-3">
			<label for="costo"
				class='col-form-label '>CostoTotal:
				(Valido solo para Prom. Absoluta)</label> <input class="form-control"
				type="number" id="costototal" name="costototal" required
				value="${promocion.costo}"></input>
			
		</div>

		<div class="mb-3">
			<label for="descuentoPorcentual"
				class='col-form-label '>DecuentoPorcentual:
				(Valido solo para Prom. Porcentual)</label> <input class="form-control"
				type="number" id="capacity" name="descuentoporcentual" required
				value="${promocion.descuentoPorcentual}"></input>
			
		</div>

		<div class="mb-3">
			<label for="preferencias" class="col-form-label">Preferencias:
				(Solo valido (" PAISAJE ")|| (" AVENTURA ") || (" DEGUSTACION "))</label> <input
				type="text" class="form-control" id="name" name="preferencias"
				required value="${attraction.nombre}">
		</div>

		<div class="mb-3">
			<label for="atracciones"
				class='col-form-label '>Atraccion
				1 (Id): </label> <input class="form-control" type="number" id="atraccion1"
				name="atraccion1" required value="${promocion.setAtracciones([atraccion1])}"></input>
			
		</div>

		<div class="mb-3">
			<label for="atracciones"
				class='col-form-label '>Atraccion
				2 (Id): </label> <input class="form-control" type="number" id="atraccion2"
				name="atraccion2" required value="${promocion.setAtracciones([atraccion2])}"></input>
			
		</div>

		<div class="mb-3">
			<label for="atracciones"
				class='col-form-label '>Atraccion
				Plus (Id): (Valido solo para Prom. AxB)</label> <input class="form-control"
				type="number" id="capacity" name="atraccionP" required
				value="${promocion.setAtracciones([atraccion3])}"></input>
			
		</div>

	</div>

	<div>
		<button type="submit" class="btn btn-primary">Guardar</button>
		<a onclick="window.history.back();" class="btn btn-secondary"
			role="button">Cancelar</a>
	</div>