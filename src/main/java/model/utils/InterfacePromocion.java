package model.utils;

import model.Atraccion;

public interface InterfacePromocion extends Comparable<InterfacePromocion> {
    Object retornarPromocion();
    Atraccion getAtraccionA();
    Atraccion getAtraccionB();
}
