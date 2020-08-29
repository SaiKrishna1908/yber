package com.wednesday.yber.controller;

import com.wednesday.yber.Service.MyUserDetailService;
import com.wednesday.yber.api.v1.auth.AuthenticationRequest;
import com.wednesday.yber.api.v1.auth.AuthenticationResponse;
import com.wednesday.yber.util.JwtUtil;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/authenticate")
public class Authentication {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private JwtUtil jwtTokenUtil;


    @PostMapping
    ResponseEntity<?> checkAuthenticate(@RequestBody AuthenticationRequest request) throws BadCredentialsException{
        System.out.println(request.getPhoneNumber()+ " "+request.getPassword());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getPhoneNumber(), request.getPassword()
        ));

        final UserDetails userDetails= myUserDetailService.loadUserByUsername(
                request.getPhoneNumber()
        );

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
