package com.smunity.api.domain.account.dto;

import com.smunity.api.domain.account.domain.User;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Boolean is_superuser;
    private Boolean is_staff;
    private Boolean is_active;
    private LocalDateTime last_login;
    private Long profile_id;

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .is_superuser(user.getIs_superuser())
                .is_staff(user.getIs_staff())
                .is_active(user.getIs_active())
                .last_login(user.getLast_login())
                .profile_id(user.getProfile().getId())
                .build();
    }
}
