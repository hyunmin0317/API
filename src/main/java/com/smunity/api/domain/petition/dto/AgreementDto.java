package com.smunity.api.domain.petition.dto;

import com.smunity.api.domain.petition.domain.Agreement;
import com.smunity.api.domain.petition.domain.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class AgreementDto {
    private Long id;
    private Long author_id;
    private Long petition_id;

    public static AgreementDto toDto(Agreement agreement) {
        return AgreementDto.builder()
                .id(agreement.getId())
                .author_id(agreement.getUser().getId())
                .petition_id(agreement.getPetition().getId())
                .build();
    }

    public static List<AgreementDto> toDtos(List<Agreement> agreementList) {
        List<AgreementDto> agreementDtoList = new ArrayList<>();
        for (Agreement agreement: agreementList)
            agreementDtoList.add(toDto(agreement));
        return agreementDtoList;
    }
}
