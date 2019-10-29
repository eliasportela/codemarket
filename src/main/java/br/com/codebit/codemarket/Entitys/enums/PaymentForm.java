package br.com.codebit.codemarket.Entitys.enums;

public enum PaymentForm {

    PAYMENT1(1, "Dinheiro"),
    PAYMENT2(1, "PicPay");

    private Integer cod;
    private String description;

    PaymentForm(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentForm toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (PaymentForm x : PaymentForm.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
