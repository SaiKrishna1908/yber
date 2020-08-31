package com.wednesday.yber.controller;

import com.wednesday.yber.Service.BookingService;
import com.wednesday.yber.Service.CabService;
import com.wednesday.yber.Service.DriverService;
import com.wednesday.yber.Service.UserService;
import com.wednesday.yber.api.v1.auth.AuthenticationRequest;
import com.wednesday.yber.api.v1.auth.AuthenticationResponse;
import com.wednesday.yber.api.v1.domain.BookingDTO;
import com.wednesday.yber.api.v1.domain.CabDTO;
import com.wednesday.yber.api.v1.domain.UserDTO;
import com.wednesday.yber.model.Booking;
import com.wednesday.yber.model.Cab;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final BookingService bookingService;

    private final CabService cabService;

    @Value("${app.baseUrl}")
    private String BASEURL;

    private final static String ROUTEURL = "/user/";

    @PostMapping("/new")
    ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO userResult = userService.createUser(userDTO);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        return new ResponseEntity<>(userResult, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        UserDTO userDTO = userService.getUserById(id);
        if(userDTO == null)
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(userDTO,HttpStatus.FOUND);
    }

    @GetMapping("/find/{userid}/{source}")
    ResponseEntity<List<CabDTO>> findCabs
            (@PathVariable Long userid, @PathVariable String source)
    {

        UserDTO userDTO = userService.getUserById(userid);
        List<CabDTO> cabs = cabService.findCabs(userDTO,source);

        return new ResponseEntity<>(cabs, HttpStatus.OK);
    }

    @PostMapping("book/{userid}/{plate}/{source}/{destination}")
    ResponseEntity<BookingDTO> bookCab(@PathVariable Long userid, @PathVariable String plate,
                                       @PathVariable String source ,@PathVariable String destination){

        UserDTO userDTO = userService.getUserById(userid);
        CabDTO cabDTO = cabService.findByPlateNumber(plate);

        BookingDTO bookingDTO = bookingService.bookRide(userDTO,cabDTO,source,destination);

        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @GetMapping("book/{userid}/bookings")
    ResponseEntity<List<BookingDTO>> getBookings(@PathVariable Long userid){

        UserDTO userDTO = userService.getUserById(userid);

        List<BookingDTO> bookingDTOS = bookingService.getPastBookings(userDTO);

        return new ResponseEntity<>(bookingDTOS, HttpStatus.OK );
    }




}
