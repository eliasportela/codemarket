package br.com.codebit.codemarket.configs;

import br.com.codebit.codemarket.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;

@Configuration
public class TestConfig {

    @Autowired
    private TestService testService;

    @Bean
    public boolean instatieteDataBase() throws ParseException {

        testService.instantiateTestDatabase();
        return true;
    }
}
