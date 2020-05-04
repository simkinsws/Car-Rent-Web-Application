package com.car.rentservice.repositories.tags;

import com.car.rentservice.modal.tags.MakeTags;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MakeTagsRepository extends JpaRepository<MakeTags, Long> {
//    @Query(value = "SELECT MAKETAGS_SEQ.next_val FROM maketag_sequence", nativeQuery = true)
//    long findCurrentNumberSequence();

    MakeTags findByMake(String make);
}
