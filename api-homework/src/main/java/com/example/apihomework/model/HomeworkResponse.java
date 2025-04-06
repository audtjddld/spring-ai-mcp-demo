package com.example.apihomework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class HomeworkResponse {
    private String uid;
    private String title;
    private String description;
    private String date;
}
