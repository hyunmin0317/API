package com.smunity.api.domain.petition.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.querydsl.core.annotations.QueryProjection;
import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.petition.entity.Petition;
import io.swagger.annotations.ApiModel;
import lombok.Data;


public class PetitionDto {
    @Data
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

        @QueryProjection
        public Response(Petition petition) {
            this.id = petition.getId();
            this.author_id = petition.getAuthor().getId();
            this.subject = petition.getSubject();
            this.content = petition.getContent();
            this.category = petition.getCategory();
            this.anonymous = petition.getAnonymous();
            this.create_date = petition.getCreateDate();
            this.end_date = petition.getEndDate();
            this.modify_date = petition.getModifyDate();
            this.status = petition.getStatus();
        }

        public static PetitionDto.Response of(Petition petition) {
            return new Response(petition);
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
