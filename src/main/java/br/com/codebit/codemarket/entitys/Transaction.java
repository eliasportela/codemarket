package br.com.codebit.codemarket.entitys;

import br.com.codebit.codemarket.entitys.enums.StatusTransaction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date dt_creation;

    @Column(nullable = false)
    private Date dt_finalization;

    @Column(nullable = false)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "transaction")
    private List<ItemTransaction> itens = new ArrayList<>();

    public Transaction() {
        setStatus(StatusTransaction.OPEN);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDt_creation() {
        return dt_creation;
    }

    public void setDt_creation(Date dt_creation) {
        this.dt_creation = dt_creation;
    }

    public Date getDt_finalization() {
        return dt_finalization;
    }

    public void setDt_finalization(Date dt_finalization) {
        this.dt_finalization = dt_finalization;
    }

    public StatusTransaction getStatus() {
        return StatusTransaction.toEnum(status);
    }

    public void setStatus(StatusTransaction status) {
        if (status != null) {
            this.status = status.getCod();
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ItemTransaction> getItens() {
        return itens;
    }

    public void setItens(List<ItemTransaction> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
