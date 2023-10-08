package com.smunity.api.domain.petition.dto;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.petition.entity.Agreement;
import com.smunity.api.domain.petition.entity.Petition;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
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

    public AgreementDto(Agreement agreement) {
        this.id = agreement.getId();
        this.user_id = agreement.getUser().getId();
        this.petition_id = agreement.getPetition().getId();
    }
}
