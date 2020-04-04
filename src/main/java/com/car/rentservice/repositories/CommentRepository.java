package com.car.rentservice.repositories;

import com.car.rentservice.modal.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments, Long> {
}
