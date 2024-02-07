package com.dylan.projet.ApiDemo.models;

import lombok.Data;

@Data
public class AuthRequest {

    private String email;

    private String password;
}
