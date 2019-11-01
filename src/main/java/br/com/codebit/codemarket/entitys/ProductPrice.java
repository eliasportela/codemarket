package br.com.codebit.codemarket.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Where(clause = "excluded = false")
public class ProductPrice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(nullable = false)
    private Double costValue = 0.0;

    @Column(nullable = false)
    private Double value = 0.0;

    private Boolean main = false;

    @Column(columnDefinition = "boolean default true")
    private Boolean enabled = true;

    @Column(columnDefinition = "boolean default false")
    private Boolean excluded = true;

    private Date excluded_at;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<ItemTransaction> itens = new ArrayList<>();

    public ProductPrice() {
    }

    public ProductPrice(String name, Double costValue, Double value, Boolean main, Product product) {
        this.name = name;
        this.costValue = costValue;
        this.value = value;
        this.main = main;
        this.product = product;
        this.enabled = true;
        this.excluded = false;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Boolean getExcluded() {
        return excluded;
    }

    public void setExcluded(Boolean excluded) {
        this.excluded = excluded;
    }

    public Date getExcluded_at() {
        return excluded_at;
    }

    public void setExcluded_at(Date excluded_at) {
        this.excluded_at = excluded_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPrice that = (ProductPrice) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
