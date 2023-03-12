package br.ada.americanas.moviebattle.config;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.Set;

@Component
public class ConstraintViolationToErrorsConverter extends SpringValidatorAdapter {

    @Autowired
    public ConstraintViolationToErrorsConverter() {
        super(Validation.buildDefaultValidatorFactory().getValidator());
    }

    public Errors convert(Set<? super ConstraintViolation<?>> source, Errors errors) {
        super.processConstraintViolations((Set) source, errors);
        return errors;
    }
}
