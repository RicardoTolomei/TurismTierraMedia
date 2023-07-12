package model.Enum;

public enum ENUMTIPO {
    AVENTURA("Aventura"),
    PAISAJE("Paisaje"),
    DEGUSTACION("Degustacion"),
	SinDefinir("SinDefinir");

    private final String tipo;

    ENUMTIPO(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
