package br.com.codebit.codemarket.entitys.enums;

public enum StatusTransaction {

    OPEN(1, "Aberta"),
    CLOSED(2, "Fechada"),
    CANCELED(3, "Cancelada");

    private Integer cod;
    private String description;

    StatusTransaction(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static StatusTransaction toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (StatusTransaction x : StatusTransaction.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
