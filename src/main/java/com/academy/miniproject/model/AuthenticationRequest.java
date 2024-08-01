package com.academy.miniproject.model;

import lombok.Data;

@Data
public class AuthenticationRequest {

	/* login request*/
    private String userName;
    private String password;
}
