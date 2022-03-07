package com.app.domain.config.validator;

import com.app.domain.config.validator.exception.ValidatorException;

import java.util.Map;
import java.util.stream.Collectors;

public interface Validator<T> {
    Map<String, String> validate(T t);

    static <T> void validate(Validator<T> validator, T t) {
        System.out.println("VALIDATOR");
        var errors = validator.validate(t);
        if (!errors.isEmpty()) {
            var message = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));
            throw new ValidatorException("[VALIDATION ERRORS]: " + message);
        }
    }
}
