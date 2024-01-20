package com.smunity.api.domain.petition.dto;

import java.time.LocalDateTime;
import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.petition.entity.Category;
import com.smunity.api.domain.petition.entity.Petition;
import com.smunity.api.domain.petition.entity.Status;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

public class PetitionDto {
    @Data
    @Builder
    @ApiModel(value = "PetitionDtoResponse")
    public static class Response {
        private Long id;
        private Long author_id;
        private String subject;
        private String content;
        private String category;
        private Boolean anonymous;
        private LocalDateTime create_date;
        private LocalDateTime end_date;
        private LocalDateTime modify_date;
        private String status;
        private Integer agreements;

        public static Response of(Petition petition) {
            return Response.builder()
                    .id(petition.getId())
                    .author_id(petition.getAuthor().getId())
                    .subject(petition.getSubject())
                    .content(petition.getContent())
                    .category(petition.getCategory().getCode())
                    .create_date(petition.getCreateDate())
                    .end_date(petition.getEndDate())
                    .modify_date(petition.getModifyDate())
                    .status(petition.getStatus().getCode())
                    .agreements(petition.getAgreements().size())
                    .build();
        }

        public static Page<Response> of(Page<Petition> petitionPage) {
            return petitionPage.map(Response::of);
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
                    .category(Category.valueOf(category))
                    .anonymous(anonymous)
                    .author(user)
                    .status(Status.IN_PROGRESS)
                    .build();
        }
    }
}