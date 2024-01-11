package com.smunity.api.domain.account.service;

import com.smunity.api.domain.account.dto.AuthDto;
import com.smunity.api.global.error.exception.RestException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class AuthService {
    private final String BASE_URL = "https://ecampus.smu.ac.kr/";

    public Map<String, String> signIn(AuthDto.Request request) {
        Connection.Response res = login(request);
        if (!res.url().toString().equals(BASE_URL))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        return res.cookies();
    }

    public AuthDto.Response getInformation(Map<String, String> cookies) {
        Document doc = getUserEditDocument(cookies);
        return AuthDto.Response.of(getById(doc, "id_firstname"), changeName(getById(doc, "id_department")), getById(doc, "id_email"));
    }

    private Connection.Response login(AuthDto.Request request) {
        try {
            return Jsoup.connect(BASE_URL.concat("login/index.php"))
                    .data("username", request.getUsername(), "password", request.getPassword())
                    .method(Connection.Method.POST)
                    .execute();
        } catch (IOException e) {
            throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Document getUserEditDocument(Map<String, String> cookies) {
        try {
            return Jsoup.connect(BASE_URL.concat("user/user_edit.php"))
                    .cookies(cookies)
                    .method(Connection.Method.GET)
                    .get();
        } catch (IOException e) {
            throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getById(Document doc, String id) {
        return doc.select("input[id=" + id + "]").val();
    }

    private String changeName(String department) {
        Map<String, String> map = Map.of("융합전자공학전공", "지능IOT융합전공", "지능·데이터융합학부", "핀테크전공");
        return map.getOrDefault(department, department);
    }
}
