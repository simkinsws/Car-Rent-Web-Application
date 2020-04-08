package com.car.rentservice;

import com.car.rentservice.modal.*;
import com.car.rentservice.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

}
@Component
class DataInsertingCommandLineRunner implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private PickUpPlaceRepository pickUpPlaceRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PasswordEncoder bCryptEncoder;

    @Override
    public void run(String... args) throws Exception {

        User newUser = new User();
        newUser.setEmail("nir.simkin@gmail.com");
        newUser.setFirstName("nir");
        newUser.setSecondName("simkin");
        newUser.setPhotoUrl("img.com");
        newUser.setPhone("0524070215");
        newUser.setPassword(bCryptEncoder.encode("12345"));
        newUser.setCars(new ArrayList<>());
        newUser.setComments(new ArrayList<>());
        newUser.setReservations(new ArrayList<>());
        userRepository.save(newUser);

        PickUpPlace pickUpPlace = new PickUpPlace();
        pickUpPlace.setPlaceId("123");
        pickUpPlace.setPlaceName("netanya");
        pickUpPlace.setLatitude(new BigDecimal(123.3));
        pickUpPlace.setLongitude(new BigDecimal(145.65));
        pickUpPlaceRepository.save(pickUpPlace);

        Comments comment1 = new Comments();
        comment1.setPost("Hey try1");
        comment1.setSerialNumber("1234567");
        comment1.setUser(newUser);
        commentRepository.save(comment1);

        Car car1 = new Car();
        car1.setSerialNumber("1234567");
        car1.setMake("Mercedes");
        car1.setModal("Mercedes benz");
        car1.setYear("2002");
        car1.setEngine("Engine V6");
        car1.setFuel("fuel");
        car1.setGear("VWR");
        car1.setWheelsDrive("RWD");
        car1.setDoors(4);
        car1.setSeats(5);
        car1.setFuelConsumption(new BigDecimal(13.5));
        car1.setFeatures(Arrays.asList("Try1", "Try2"));
        car1.setPricePerDay(new BigDecimal(40.5));
        car1.setDistanceIncluded(133);
        car1.setAbout("About car");
        car1.setPickUpPlace(pickUpPlace);
        car1.setImageUrl(Arrays.asList("img.com", "img2.com"));
        car1.setUser(newUser);
        carRepository.save(car1);
        Car car2 = new Car();

        car2.setSerialNumber("1234567");
        car2.setMake("Mercedes");
        car2.setModal("Mercedes benz");
        car2.setYear("2002");
        car2.setEngine("Engine V6");
        car2.setFuel("fuel");
        car2.setGear("VWR");
        car2.setWheelsDrive("RWD");
        car2.setDoors(4);
        car2.setSeats(5);
        car2.setFuelConsumption(new BigDecimal(13.5));
        car2.setFeatures(Arrays.asList("Try1", "Try2"));
        car2.setPricePerDay(new BigDecimal(40.5));
        car2.setDistanceIncluded(133);
        car2.setAbout("About car2");
        car2.setPickUpPlace(pickUpPlace);
        car2.setImageUrl(Arrays.asList("img.com", "img4.com"));
        car2.setUser(newUser);
        carRepository.save(car2);


        Reservation reservation1 = new Reservation();
        reservation1.setAmount(new BigDecimal(123.5));
        reservation1.setBookingDate(LocalDateTime.now());
        reservation1.setStartDateTime(LocalDateTime.now());
        reservation1.setEndDateTime(LocalDateTime.now().plusDays(1));
        reservation1.setOrderNumber("1");
        reservation1.setConfirmationCode("c234512");
        reservation1.setUser(newUser);
        reservationRepository.save(reservation1);
        Reservation reservation2 = new Reservation();
        reservation2.setAmount(new BigDecimal(123.5));
        reservation2.setBookingDate(LocalDateTime.now());
        reservation2.setStartDateTime(LocalDateTime.now());
        reservation2.setEndDateTime(LocalDateTime.now().plusDays(1));
        reservation2.setOrderNumber("1");
        reservation2.setConfirmationCode("c234513");
        reservation2.setUser(newUser);
        reservationRepository.save(reservation2);
    }
}
