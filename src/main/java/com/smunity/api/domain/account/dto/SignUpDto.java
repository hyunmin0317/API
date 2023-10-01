package com.smunity.api.domain.account.dto;

import com.smunity.api.domain.account.domain.Department;
import com.smunity.api.domain.account.domain.Profile;
import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.account.domain.Year;
import lombok.Data;


@Data
public class SignUpDto {
    private String username;
    private String password;
    private String email;
    private Boolean is_admin;
    private String name;
    private String department;

    public User toUserEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .is_superuser(is_admin)
                .is_staff(is_admin)
                .is_active(true)
                .build();
    }

    public Profile toProfileEntity(User user, Year year, Department department) {
        return Profile.builder()
                .name(name)
                .user(user)
                .year(year)
                .department(department)
                .build();
    }
}
