package com.smunity.api.domain.petition.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.petition.entity.Petition;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;


public class PetitionDto {
    @Data
    @Builder
    @ApiModel(value = "PetitionDtoResponse")
    public static class Response {
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

        public static PetitionDto.Response of(Petition petition) {
            return PetitionDto.Response.builder()
                    .id(petition.getId())
                    .author_id(petition.getAuthor().getId())
                    .subject(petition.getSubject())
                    .content(petition.getContent())
                    .category(petition.getCategory())
                    .create_date(petition.getCreateDate())
                    .end_date(petition.getEndDate())
                    .modify_date(petition.getModifyDate())
                    .status(petition.getStatus())
                    .build();
        }

        public static List<PetitionDto.Response> of(List<Petition> petitionList) {
            return petitionList.stream().map(PetitionDto.Response::of).collect(Collectors.toList());
        }
    }

    @Data
    @ApiModel(value = "PetitionDtoRequest")
    public static class Request {
        private String subject;
        private String content;
        private Integer category;
        private Boolean anonymous;

        public Petition toEntity(User user) {
            return Petition.builder()
                    .subject(subject)
                    .content(content)
                    .category(category)
                    .anonymous(anonymous)
                    .author(user)
                    .status(1)
                    .build();
        }
    }
}
