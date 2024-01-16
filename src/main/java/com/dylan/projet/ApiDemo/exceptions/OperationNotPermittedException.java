package com.dylan.projet.ApiDemo.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OperationNotPermittedException extends RuntimeException{

    private final String errorMessage;

    private final String source;

    private final String operationId;

    private final String dependency;
}
