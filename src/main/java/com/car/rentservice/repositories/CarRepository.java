package com.car.rentservice.repositories;

import com.car.rentservice.modal.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
