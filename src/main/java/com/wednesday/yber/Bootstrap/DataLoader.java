package com.wednesday.yber.Bootstrap;

import com.wednesday.yber.model.*;
import com.wednesday.yber.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    final private BookingsRepository bookingsRepository;
    final private CabDetailsRepository cabDetailsRepository;
    final private DriverRepository driverRepository;
    final private UserRepository userRepository;
    final private CabRepository cabRepository;

    public DataLoader(BookingsRepository bookingsRepository, CabDetailsRepository cabDetailsRepository, DriverRepository driverRepository, UserRepository userRepository, CabRepository cabRepository) {
        this.bookingsRepository = bookingsRepository;
        this.cabDetailsRepository = cabDetailsRepository;
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
        this.cabRepository = cabRepository;
    }


    @Override
    public void run(String... args) throws Exception {


        //Driver
        Driver naresh = Driver.builder().firstName("Naresh").lastName("Mongdala").phoneNumber("9881525346").rating(4.0).build();
        Driver mahesh = Driver.builder().firstName("mahesh").lastName("satyam").phoneNumber("12345678").rating(4.9).build();
        Driver suresh = Driver.builder().firstName("suresh").lastName("shaman").phoneNumber("437475857").rating(4.8).build();

        naresh = driverRepository.save(naresh);
        mahesh = driverRepository.save(mahesh);
        suresh= driverRepository.save(suresh);



        //Cab And CabDetails
        Cab nareshCab = Cab.builder().plateNumber("1234AB").isAvailable(true).longitude(78.55).latitude(17.46).driver(naresh)
                        .user(new ArrayList<>())
                        .longitude(15.21).build();

        nareshCab = cabRepository.save(nareshCab);


        CabDetails nareshCabDetails = CabDetails.builder().cab(nareshCab).carname("Honda").totalTrips(12).tripsToday(2)
                                        .type("MINI").build();

        nareshCabDetails = cabDetailsRepository.save(nareshCabDetails);
        nareshCabDetails.setCab(nareshCab);
        nareshCab.setCabDetails(nareshCabDetails);
        cabRepository.save(nareshCab);
        cabDetailsRepository.save(nareshCabDetails);


        Cab maheshCab = Cab.builder().plateNumber("57234").driver(mahesh).isAvailable(true).latitude(17.40)
                .longitude(78.21)
                .user(new ArrayList<>())
                .longitude(17.21).build();

        CabDetails maheshCabDetails = CabDetails.builder().cab(nareshCab).carname("Tata").totalTrips(56).tripsToday(9)
                .type("SUV").build();

        maheshCab = cabRepository.save(maheshCab);
        maheshCabDetails.setCab(maheshCab);
        maheshCabDetails = cabDetailsRepository.save(maheshCabDetails);
        maheshCab.setCabDetails(maheshCabDetails);
        cabRepository.save(maheshCab);
        cabDetailsRepository.save(maheshCabDetails);

        Cab sureshCab = Cab.builder().plateNumber("128377").driver(mahesh).isAvailable(false).latitude(14.32)
                .user(new ArrayList<>())
                .longitude(80.43).build();
        CabDetails sureshCabDetails = CabDetails.builder().cab(sureshCab).carname("Hyundai").totalTrips(23)
                .tripsToday(4)
                .type("Sedan").build();

        sureshCab = cabRepository.save(sureshCab);
        sureshCabDetails.setCab(sureshCab);
        sureshCabDetails = cabDetailsRepository.save(sureshCabDetails);
        sureshCab.setCabDetails(sureshCabDetails);
        cabRepository.save(sureshCab);
        cabDetailsRepository.save(sureshCabDetails);

        //User Details

        User ramu = User.builder().latitude(12.34).longitude(13.21).firstName("ramu").phoneNumber("123746363")
                .lastName("chander").password("password")
                .build();
        User pooja = User.builder().latitude(14.30).longitude(12.21).firstName("pooja").lastName("chakra")
                .phoneNumber("129485738").password("password")
                .build();

        User teja= User.builder().latitude(11.34).longitude(10.21).firstName("teja").lastName("Yogi")
                .phoneNumber("2847593759").password("password")
                .build();

        ramu = userRepository.save(ramu);
        pooja = userRepository.save(pooja);
        teja = userRepository.save(teja);

        ramu.getCabList().add(sureshCab);
        ramu.getCabList().add(nareshCab);

        ramu = userRepository.save(ramu);

        sureshCab.getUser().add(ramu);
        nareshCab.getUser().add(ramu);

        cabRepository.save(sureshCab);
        cabRepository.save(nareshCab);


        pooja.getCabList().add(maheshCab);

        pooja = userRepository.save(pooja);

        maheshCab.getUser().add(pooja);
        cabRepository.save(maheshCab);


        //Booking

        Booking ramuSureshBooking = new Booking();
        ramuSureshBooking.setCab(sureshCab);
        ramuSureshBooking.setDate(LocalDateTime.now());
        ramuSureshBooking.setUser(ramu);
        ramuSureshBooking.setSource("Gaganpahad");
        ramuSureshBooking.setDestination("Airport");
        bookingsRepository.save(ramuSureshBooking);

        Booking ramuNareshBooking = new Booking();
        ramuNareshBooking.setCab(nareshCab);
        ramuNareshBooking.setDate(LocalDateTime.of(2020,8,27,5,20,00));
        ramuNareshBooking.setUser(ramu);
        ramuNareshBooking.setSource("Sainikpuri");
        ramuNareshBooking.setDestination("Secundrabad");
        bookingsRepository.save(ramuNareshBooking);

        Booking poojaMaheshBooking = new Booking();
        poojaMaheshBooking.setCab(maheshCab);
        poojaMaheshBooking.setDate(LocalDateTime.of(2020,8,28,6,20,23));
        poojaMaheshBooking.setSource("Banjara Hills");
        poojaMaheshBooking.setDestination("Jubilee Hills");
        poojaMaheshBooking.setUser(pooja);
        bookingsRepository.save(poojaMaheshBooking);
    }
}
