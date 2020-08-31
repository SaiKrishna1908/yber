package com.wednesday.yber.Service;

import com.wednesday.yber.model.User;
import com.wednesday.yber.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    private  UserDetails userDetails ;

    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByPhoneNumber(s);
        System.out.println(user.get().getPhoneNumber());
        userDetails =  new org.springframework.security.core.userdetails.User(user.get().getPhoneNumber()
                                            ,user.get().getPassword(), new ArrayList<>());
        return  userDetails;
    }
}
