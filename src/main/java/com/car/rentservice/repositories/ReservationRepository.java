package com.car.rentservice.repositories;

import com.car.rentservice.modal.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findBySerialNumber(String serialNumber);
}
