package br.com.codebit.codemarket.entitys;

import br.com.codebit.codemarket.dtos.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Where(clause = "excluded = false")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "boolean default true")
    private Boolean enabled;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<ProductPrice> prices;

    @JsonIgnore
    @Column(columnDefinition = "boolean default false")
    private Boolean excluded = false;

    @JsonIgnore
    private Date excluded_at;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
        enabled = true;
        excluded = false;
    }

    public Product(ProductDTO obj) {
        name = obj.getName();
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<ProductPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<ProductPrice> prices) {
        this.prices = prices;
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
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
