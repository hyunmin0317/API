package com.smunity.api.domain.account.dto;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.petition.domain.Agreement;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
                .build();
    }

    public static List<UserDto> toAgreementDtos(List<Agreement> agreementList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (Agreement agreement: agreementList)
            userDtoList.add(toDto(agreement.getAuthor()));
        return userDtoList;
    }
}
