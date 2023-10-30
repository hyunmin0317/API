package com.smunity.api.domain.petition.dto;

import java.time.LocalDateTime;
import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.petition.entity.Petition;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
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
    private static ModelMapper modelMapper = new ModelMapper();

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

    public static PetitionDto of(Petition petition) {
        return modelMapper.map(petition, PetitionDto.class);
    }
}
