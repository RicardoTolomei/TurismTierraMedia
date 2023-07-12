package model.utils;

import java.util.Comparator;

import model.Atraccion;

public class ComparadorAtraccion implements Comparator<Atraccion> {

    @Override
    public int compare(Atraccion atraccionA, Atraccion atraccionB) {
        return (int) Math.signum(atraccionB.compareTo(atraccionA));
    }
}
