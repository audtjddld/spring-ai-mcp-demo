package com.example.apihomework.dao;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    @Column(nullable = false)
    @Comment("UID")
    private String uid;
    @Column(nullable = false)
    @Comment("제목")
    private String title;
    @Column(nullable = false)
    @Comment("설명")
    private String description;
    @Column(nullable = false)
    @Comment("제출 마감일")
    private String date;
    @CreationTimestamp
    @Comment("등록일시")
    private LocalDateTime regDateTime;
    @UpdateTimestamp
    @Comment("최종 수정일시")
    private LocalDateTime lastUpdateTime;

    public static Homework of(String title, String description, String date) {
        Homework homework = new Homework();
        homework.uid = UUID.randomUUID().toString().replace("-", "");
        homework.title = title;
        homework.description = description;
        homework.date = date;
        return homework;
    }

    public void updateDetails(String title, String description, String date) {
        this.title = title == null ? this.title : title;
        this.description = description == null ? this.description : description;
        this.date = date = date == null ? this.date : date;
    }
}
