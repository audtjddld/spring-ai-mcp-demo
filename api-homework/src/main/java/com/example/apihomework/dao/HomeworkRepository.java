package com.example.apihomework.dao;

import com.example.apihomework.model.HomeworkResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {

    @Query("SELECT new com.example.apihomework.model.HomeworkResponse(a.uid, a.title, a.description, a.date) FROM Homework a")
    List<HomeworkResponse> findAllAsDTO();

    Optional<Homework> findByUid(String uid);

    void deleteByUid(String uid);
}
