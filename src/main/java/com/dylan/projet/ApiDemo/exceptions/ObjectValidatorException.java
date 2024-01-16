package com.dylan.projet.ApiDemo.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;
@Getter
@RequiredArgsConstructor
public class ObjectValidatorException extends RuntimeException {


    private final Set<String> violations;

    private final String source;
}
