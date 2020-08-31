package com.wednesday.yber.controller;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wednesday.yber.Service.*;
import com.wednesday.yber.api.v1.domain.CabDTO;
import com.wednesday.yber.api.v1.domain.UserDTO;
import com.wednesday.yber.mapper.CabDTOMapper;
import com.wednesday.yber.mapper.CabMapper;
import com.wednesday.yber.mapper.UserDTOMapper;
import com.wednesday.yber.mapper.UserMapper;
import com.wednesday.yber.model.*;
import com.wednesday.yber.repository.BookingsRepository;
import com.wednesday.yber.repository.CabRepository;
import com.wednesday.yber.repository.IDRepository;
import com.wednesday.yber.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;
    @Mock
    BookingService bookingService;
    @Mock
    CabService cabService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private  BookingsRepository bookingsRepository;
    @Mock
    private  CabRepository cabRepository;
    @Mock
    private  JOpenCageGeocoder jOpenCageGeocoder;
    @Mock
    private IDRepository idRepository;

    static final String BASEURL = "http://localhost:8080/user/";

    UserController userController;

    MockMvc mockMvc;

    //mappers
    UserMapper userMapper ;
    UserDTOMapper userDTOMapper;
    CabMapper cabMapper;

    //data
    ID id;
    User user;
    Cab cab;
    Driver driver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);



        userService = new UserServiceImpl(userRepository, idRepository);
        bookingService = new BookingServiceImpl(bookingsRepository, cabRepository, userRepository, jOpenCageGeocoder);
        cabService = new CabServiceImpl(jOpenCageGeocoder, cabRepository);
        userMapper = UserMapper.INSTANCE;
        userDTOMapper = UserDTOMapper.INSTANCE;
        userController = new UserController(userService, bookingService, cabService);

        mockMvc= MockMvcBuilders.standaloneSetup(userController).build();


        driver = Driver.builder().firstName("Rakesh").phoneNumber("1234567834").build();
        cab = Cab.builder().plateNumber("AP10BD1234").isAvailable(true).longitude(12.45).driver(driver).
                latitude(12.23).build();

        id = new ID();
        id.setId(1L);

        user = User.builder().latitude(12.09).longitude(23.45).phoneNumber("1456890").password("password").build();

    }

    //testing controllers

    @Test
    void createUser() throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setLatitude(12.09);
        userDTO.setLongitude(23.54);
        userDTO.setPhoneNumber("1456890");
        userDTO.setPassword("password");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String requestJson = ow.writeValueAsString(userDTO);

        ID id = new ID();
        id.setId(1L);



        when(userRepository.findByPhoneNumber(anyString())).thenReturn(Optional.empty());
        when(idRepository.save(any())).thenReturn(id);

        User savedUser = userDTOMapper.UserDTOToUser(userDTO);
        savedUser.setId(1L);
        savedUser.setId(id.getId());
        id.setUser(savedUser);

        when(userRepository.save(any())).thenReturn(savedUser);

        mockMvc.perform(post(BASEURL+"new").contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson)).andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception {

        user.setId(id.getId());
        id.setUser(user);

        when(idRepository.findById(any())).thenReturn(Optional.of(id));

        mockMvc.perform(get(BASEURL+"/1")).andExpect(status().isFound());

    }


    @Test
    void findCabs() throws Exception {
        UserDTO userDTO = userMapper.UserToUserDTO(user);
        CabDTO cabDTO = new CabDTO();
        cabDTO.setPlateNumber(cab.getPlateNumber());
        cabDTO.setLatitude(cab.getLatitude());
        cabDTO.setLongitude(cab.getLongitude());



        String source = "Sainikpuri";
        String destinaiton = "Ameerpet";

        user.setId(id.getId());
        id.setUser(user);
        List<Cab> cabs = new ArrayList<>();
        cabs.add(cab);

        when(idRepository.findById(any())).thenReturn(Optional.of(id));
        when(jOpenCageGeocoder.forward(any())).thenReturn(new JOpenCageResponse());


        mockMvc.perform(get(BASEURL+"/find/1/"+source)).andExpect(status().isBadRequest());
    }

    @Test
    void bookCab() throws Exception {

        id.setUser(user);
        when(idRepository.findById(any())).thenReturn(Optional.of(id));
        when(cabRepository.findCabByPlateNumber(anyString())).thenReturn(Optional.of(cab));
        when(userRepository.findByPhoneNumber(anyString())).thenReturn(Optional.of(user));
        when(cabRepository.findCabByPlateNumber(cab.getPlateNumber())).thenReturn(Optional.of(cab));
        when(bookingsRepository.save(any())).thenReturn(Booking.builder().id(1L).build());

        mockMvc.perform(post(BASEURL+"book/1/232123/sainikpuri/ameerpet")).andExpect(status().isOk());
    }

    @Test
    void getBookings() throws Exception {

        id.setUser(user);
        Booking booking = Booking.builder().id(1L).user(user).cab(cab).build();
        Booking booking1 = Booking.builder().id(2L).user(user).cab(cab).build();

        List<Booking> bookings = new ArrayList<>();

//        when(bookingsRepository.findById(anyLong())).thenReturn(Optional.of(booking));
        when(idRepository.findById(anyLong())).thenReturn(Optional.of(id));
        when(bookingsRepository.findAllByUserPhoneNumber(anyString()))
                .thenReturn(bookings);

        mockMvc.perform(get(BASEURL+"book/1/bookings")).andExpect(status().isOk());
    }
}