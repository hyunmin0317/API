package com.smunity.api.domain.petition.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    ACADEMIC("학사"),
    REGISTRATION("등록/장학"),
    STUDENT("학생생활"),
    CAREER("진로취업"),
    OTHER("기타");

    private final String code;

    public static Category valueOf(int ordinal) {
        for (Category category : Category.values()) {
            if (category.ordinal() == ordinal) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid Category code: " + ordinal);
    }
}