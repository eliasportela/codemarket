package br.com.codebit.codemarket.Entitys.enums;

public enum TypeItemTransaction {

    PRODUCT(1, "Produto"),
    PAYMENT(2, "Pagamento");

    private Integer cod;
    private String description;

    TypeItemTransaction(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static TypeItemTransaction toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (TypeItemTransaction x : TypeItemTransaction.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
