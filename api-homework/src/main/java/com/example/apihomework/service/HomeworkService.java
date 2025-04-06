package com.example.apihomework.service;

import com.example.apihomework.dao.Homework;
import com.example.apihomework.dao.HomeworkRepository;
import com.example.apihomework.model.HomeworkRequest;
import com.example.apihomework.model.HomeworkResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HomeworkService {
    private final HomeworkRepository homeworkRepository;

    public HomeworkService(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    public void save(HomeworkRequest.Save request) {
        homeworkRepository.save(Homework.of(request.getTitle(), request.getDescription(), request.getDate()));
    }

    public List<HomeworkResponse> findAll() {
        return homeworkRepository.findAllAsDTO();
    }

    public void update(final String uid, HomeworkRequest.Update request) {
        Homework homework = homeworkRepository.findByUid(uid).orElseThrow(() -> new IllegalArgumentException("Invalid UID"));
        homework.updateDetails(request.getTitle(), request.getDescription(), request.getDate());
    }

    public void delete(final String uid) {
        homeworkRepository.deleteByUid(uid);
    }
}
