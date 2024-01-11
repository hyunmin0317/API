package com.smunity.api.domain.petition.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    IN_PROGRESS("진행중"),
    AWAITING_RESPONSE("답변대기"),
    RESPONSE_COMPLETED("답변완료"),
    EXPIRED("만료"),
    REJECTED("반려");

    private final String code;
}
