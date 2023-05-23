package se.atg.harrykart.configuration;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import se.atg.harrykart.validations.XmlValidator;

@Configuration
public class ValidationConfig {

    @Bean
    Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    XmlValidator xmlSchemaValidator() throws Exception {
        return new XmlValidator();
    }
    
}

