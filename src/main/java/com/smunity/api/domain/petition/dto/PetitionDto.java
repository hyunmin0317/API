package com.smunity.api.domain.petition.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.petition.domain.Petition;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PetitionDto {
    private Long id;
    private Long author_id;
    private String subject;
    private String content;
    private Integer category;
    private Boolean anonymous;
    private LocalDateTime create_date;
    private LocalDateTime end_date;
    private LocalDateTime modify_date;
    private Integer status;

    public Petition toEntity(User user) {
        return Petition.builder()
                .subject(subject)
                .content(content)
                .category(category)
                .anonymous(anonymous)
                .end_date(end_date)
                .status(status)
                .author(user)
                .build();
    }

    public static PetitionDto toDto(Petition petition) {
        return PetitionDto.builder()
                .id(petition.getId())
                .subject(petition.getSubject())
                .content(petition.getContent())
                .category(petition.getCategory())
                .anonymous(petition.getAnonymous())
                .create_date(petition.getCreate_date())
                .modify_date(petition.getModify_date())
                .end_date(petition.getEnd_date())
                .status(petition.getStatus())
                .author_id(petition.getAuthor().getId())
                .build();
    }

    public static List<PetitionDto> toDtos(List<Petition> petitionList) {
        List<PetitionDto> petitionDtoList = new ArrayList<>();
        for (Petition petition: petitionList)
            petitionDtoList.add(toDto(petition));
        return petitionDtoList;
    }
}
