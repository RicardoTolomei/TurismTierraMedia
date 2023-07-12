package model.utils;

import java.util.Comparator;

import model.Promocion;

public class ComparadorPromocion implements Comparator<Promocion> {

    @Override
    public int compare(Promocion promocionA, Promocion promocionB) {
        return (int) Math.signum(promocionB.compareTo(promocionA));
    }
}
