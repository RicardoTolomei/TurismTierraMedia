package model;

import static org.junit.Assert.*;

import org.junit.Test;

import model.TipoDeAtraccion;
import model.Enum.ENUMTIPO;

public class TipoDeAtraccionTest {

	@Test
	public void creacionTest() {
		TipoDeAtraccion tda = new TipoDeAtraccion(1, 2, "aaaa", "bbbb");
		assertNotNull(tda);
		TipoDeAtraccion tda2 = new TipoDeAtraccion(2, "aaaa", "bbbb");
		assertNotNull(tda2);
	}
	
	@Test
	public void ComprobarAsignarValoresTest() {
		TipoDeAtraccion tda = new TipoDeAtraccion( 2, "Usuario", "Degustacion");
		
		int valor1Esperado = 2;
		int valor1Obtenido = tda.getIdReferencia();
		assertEquals(valor1Esperado, valor1Obtenido);
		
		String valor2Esperado = "Usuario";
		String valor2Obtenido = tda.getTipoDelObjeto();
		assertEquals(valor2Esperado, valor2Obtenido);
		
		String valor3Esperado = "Degustacion";
		String valor3Obtenido = tda.getTipoFavorito();
		assertEquals(valor3Esperado, valor3Obtenido);
	}
	
	@Test
	public void asignarPreferenciaTest() {
		TipoDeAtraccion tda = new TipoDeAtraccion(1, 2, "test1", "nada");
		assertEquals(ENUMTIPO.SinDefinir, tda.getPreferencia());
		
		TipoDeAtraccion tda1 = new TipoDeAtraccion(1, 2, "test2", "Degustacion");
		assertEquals(ENUMTIPO.DEGUSTACION, tda1.getPreferencia());
		
		TipoDeAtraccion tda2 = new TipoDeAtraccion(1, 2, "test3", "Paisaje");
		assertEquals(ENUMTIPO.PAISAJE, tda2.getPreferencia());
		
		TipoDeAtraccion tda3 = new TipoDeAtraccion(1, 2, "test4", "Aventura");
		assertEquals(ENUMTIPO.AVENTURA, tda3.getPreferencia());
	}

}
