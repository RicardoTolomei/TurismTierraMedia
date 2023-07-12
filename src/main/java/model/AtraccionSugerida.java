package model;

import model.utils.InterfacePromocion;

public class AtraccionSugerida {
    private final Atraccion[] atracciones;
    private final InterfacePromocion promocion;
    private final int total;

    public AtraccionSugerida(Atraccion[] atracciones, InterfacePromocion promocion, int total) {
        this.atracciones = atracciones;
        this.promocion = promocion;
        this.total = total;
    }

    public Atraccion[] getAtracciones() {
        return atracciones;
    }

    public InterfacePromocion getPromocion() {
        return promocion;
    }

    public int getTotal() {
        return total;
    }
}