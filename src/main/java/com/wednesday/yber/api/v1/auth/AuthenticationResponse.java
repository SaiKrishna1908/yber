package com.wednesday.yber.api.v1.auth;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private Long id;
    private String jwt;

    public AuthenticationResponse(String jwt){
        this.jwt = jwt;
    }
}
