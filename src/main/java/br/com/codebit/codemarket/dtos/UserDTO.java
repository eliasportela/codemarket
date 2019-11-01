package br.com.codebit.codemarket.dtos;

import br.com.codebit.codemarket.entitys.enums.Profile;
import br.com.codebit.codemarket.services.validations.UserEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Campo obrigatório")
    private String name;

    @NotEmpty(message = "Campo obrigatório")
    @Email(message = "Email inválido")
    @UserEmail
    private String email;

    @Pattern(regexp = "^(\\S{6,})?$", message = "A senha deve ter no mínimo 6 caracteres")
    private String password;

    @NotNull(message = "Campo obrigatório")
    private Boolean enabled;

    @NotNull(message = "Campo obrigatório")
    private Set<Profile> profile = new HashSet<>();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Profile> getProfile() {
        return profile;
    }

    public void setProfile(Set<Profile> profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO user = (UserDTO) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}