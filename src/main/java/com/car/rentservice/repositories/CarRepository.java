package com.car.rentservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.car.rentservice.modal.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
	Optional<Car> findBySerialNumber(String serialNumber);

	@Query(value = "SELECT * FROM Car c WHERE c.user_id = :userId", nativeQuery = true)
	List<Car> findByUserId(@Param("userId") Long userId);
}
