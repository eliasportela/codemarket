package br.com.codebit.codemarket.dtos;

import br.com.codebit.codemarket.entitys.ProductPrice;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ProductPriceDTO implements Serializable {

    private Integer id;
    private String name;

    @Min(value = 0, message = "O valor mínimo é 0")
    private Double costValue = 0.0;

    @Min(value = 0, message = "O valor mínimo é 0")
    private Double value = 0.0;

    @NotNull(message = "Valor obrigatório")
    private Boolean main = false;

    @NotNull(message = "Valor obrigatório")
    private Boolean enabled = true;

    //private String namePrice;

    public ProductPriceDTO() {
    }

    public ProductPriceDTO(ProductPrice obj) {
        id = obj.getId();
        name = obj.getName();
        costValue = obj.getCostValue();
        value = obj.getValue();
        main = obj.getMain();
        enabled = obj.getEnabled();

        //namePrice = (!obj.getMain() && obj.getName() != null) ? obj.getProduct().getName() + " - "+  obj.getName() : obj.getProduct().getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCostValue() {
        return costValue;
    }

    public void setCostValue(Double costValue) {
        this.costValue = costValue;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}