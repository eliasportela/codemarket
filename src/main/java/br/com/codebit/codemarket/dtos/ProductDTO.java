package br.com.codebit.codemarket.dtos;

import br.com.codebit.codemarket.entitys.Product;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String name;

    @NotNull(message = "Preenchimento obrigatório")
    private Boolean enabled;

    @Valid
    private List<ProductPriceDTO> prices = new ArrayList<>();

    public ProductDTO() {
    }

    public ProductDTO(Product obj) {
        id = obj.getId();
        name = obj.getName();
        enabled = obj.getEnabled();
        prices.addAll(obj.getPrices().stream().map(x -> new ProductPriceDTO(x)).collect(Collectors.toSet()));
    }

    public Product fromDTO() {
        return new Product(name);
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

    public List<ProductPriceDTO> getPrices() {
        return prices;
    }

    public void setPrices(List<ProductPriceDTO> prices) {
        this.prices = prices;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
