package com.smunity.api.domain.petition.dto;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.petition.entity.Agreement;
import com.smunity.api.domain.petition.entity.Petition;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AgreementDto {
    private Long id;
    private Long user_id;
    private Long petition_id;

    public static Agreement toEntity(User user, Petition petition) {
        return Agreement.builder()
                .user(user)
                .petition(petition)
                .build();
    }

    public static AgreementDto of(Agreement agreement) {
        return AgreementDto.builder()
                .id(agreement.getId())
                .user_id(agreement.getUser().getId())
                .petition_id(agreement.getPetition().getId())
                .build();
    }
}
