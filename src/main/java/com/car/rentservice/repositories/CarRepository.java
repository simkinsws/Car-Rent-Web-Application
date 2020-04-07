package com.car.rentservice.repositories;

import com.car.rentservice.modal.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findBySerialNumber(String serialNumber);
}
