package br.com.codebit.codemarket.services.validations;

import br.com.codebit.codemarket.controllers.exception.FieldMessage;
import br.com.codebit.codemarket.entitys.User;
import br.com.codebit.codemarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserEmailValidator implements ConstraintValidator<UserEmail, String> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;


    @Override
    public void initialize(UserEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();
        Integer uriId = null;

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (map.containsKey("id")) {
            uriId = Integer.parseInt(map.get("id"));
        }

        User aux = userRepository.findByEmail(email);
        return aux == null || (uriId != null && uriId.equals(aux.getId()));
    }
}