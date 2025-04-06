package com.example.apihomework.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class HomeworkRequest {
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class Save {
        @NotBlank(message = "제목은 필수조건 입니다")
        private String title;
        @NotBlank(message = "설명은 필수조건 입니다")
        private String description;
        @NotBlank(message = "제출 마감일은 필수조건 입니다")
        private String date;
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class Details {
        @NotNull(message = "숙제ID 는 필수조건 입니다")
        private String uid;
        private String title;
        private String description;
        private String date;
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class Update {
        @NotBlank(message = "제목은 필수조건 입니다")
        private String title;
        @NotBlank(message = "설명은 필수조건 입니다")
        private String description;
        @NotBlank(message = "제출 마감일은 필수조건 입니다")
        private String date;

    }
}
