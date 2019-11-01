package br.com.codebit.codemarket.dtos;

import br.com.codebit.codemarket.services.validations.UserEmail;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class UserSaveDTO implements Serializable {

    @NotEmpty(message = "Campo obrigatório")
    private String name;

    @NotEmpty(message = "Campo obrigatório")
    @Email(message = "Email inválido")
    @UserEmail
    private String email;

    @NotEmpty(message = "Campo obrigatório")
    @Length(min = 6, max = 60, message = "A senha deve ter no mínimo 6 caracteres e no maximo 60")
    private String password;

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

}