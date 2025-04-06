package com.example.apihomework.controller;

import com.example.apihomework.model.HomeworkRequest;
import com.example.apihomework.model.HomeworkResponse;
import com.example.apihomework.service.HomeworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/homeworks")
public class HomeworkController {
    private final HomeworkService homeworkService;

    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @PostMapping("")
    public ResponseEntity<Void> createHomework(@RequestBody HomeworkRequest.Save request) {
        homeworkService.save(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<HomeworkResponse>> findAllHomework() {
        return ResponseEntity.ok(homeworkService.findAll());
    }

    @PutMapping("/{uid}")
    public ResponseEntity<Void> updateHomework(@PathVariable String uid,
                                               @RequestBody HomeworkRequest.Update request) {
        homeworkService.update(uid, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<Void> deleteHomework(@PathVariable String uid) {
        homeworkService.delete(uid);
        return ResponseEntity.ok().build();
    }
}
