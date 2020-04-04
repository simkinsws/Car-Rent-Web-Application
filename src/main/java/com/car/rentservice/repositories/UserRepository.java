package com.car.rentservice.repositories;

import com.car.rentservice.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
