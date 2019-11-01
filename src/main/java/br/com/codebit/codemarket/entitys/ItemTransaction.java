package br.com.codebit.codemarket.entitys;

import br.com.codebit.codemarket.entitys.enums.PaymentForm;
import br.com.codebit.codemarket.entitys.enums.TypeItemTransaction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class ItemTransaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer type;

    @Column(nullable = false)
    private Date dt_item;

    @Column(nullable = false)
    private Double quantity = 0.0;

    @Column(nullable = false)
    private Double value = 0.0;

    @Column(nullable = false)
    private Integer paymentForm;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "price_id")
    private ProductPrice product;

    private String observation;

    @Column(columnDefinition = "boolean default true")
    private Boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeItemTransaction getType() {
        return TypeItemTransaction.toEnum(type);
    }

    public void setType(TypeItemTransaction type) {
        if (type != null) {
            this.type = type.getCod();
        }
    }

    public Date getDt_item() {
        return dt_item;
    }

    public void setDt_item(Date dt_item) {
        this.dt_item = dt_item;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public PaymentForm getPaymentForm() {
        return PaymentForm.toEnum(paymentForm);
    }

    public void setPaymentForm(PaymentForm paymentForm) {
        this.paymentForm = paymentForm.getCod();
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public ProductPrice getProduct() {
        return product;
    }

    public void setProduct(ProductPrice product) {
        this.product = product;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemTransaction that = (ItemTransaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
