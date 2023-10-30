package com.smunity.api.domain.petition.dto;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.petition.entity.Respond;
import com.smunity.api.domain.petition.entity.Petition;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;


public class RespondDto {
    @Data
    @Builder
    @ApiModel(value = "RespondDtoResponse")
    public static class Response {
        private Long id;
        private Long author_id;
        private Long petition_id;
        private String content;
        private LocalDateTime create_date;
        private LocalDateTime modify_date;

        public static RespondDto.Response of(Respond respond) {
            return RespondDto.Response.builder()
                    .id(respond.getId())
                    .author_id(respond.getAuthor().getId())
                    .petition_id(respond.getPetition().getId())
                    .content(respond.getContent())
                    .create_date(respond.getCreateDate())
                    .modify_date(respond.getModifyDate())
                    .build();
        }
    }

    @Data
    @ApiModel(value = "RespondDtoRequest")
    public static class Request {
        private String content;

        public Respond toEntity(User user, Petition petition) {
            return Respond.builder()
                    .content(content)
                    .author(user)
                    .petition(petition)
                    .build();
        }
    }
}
