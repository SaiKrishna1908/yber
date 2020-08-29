package com.wednesday.yber.Service;

import com.wednesday.yber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private final UserService userService;
    private  UserDetails userDetails ;

    public MyUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByPhoneNumber(s);
        userDetails =  new org.springframework.security.core.userdetails.User(user.getPhoneNumber()
                                            ,user.getPassword(), new ArrayList<>());
        return  userDetails;
    }
}
