package com.dylan.projet.ApiDemo.models;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class AuthResponse {

    private String token;
}
