package com.smunity.api.domain.account.service.impl;

import com.smunity.api.domain.account.dto.AuthDto;
import com.smunity.api.domain.account.dto.UserDto;
import com.smunity.api.domain.account.service.AuthService;
import com.smunity.api.global.error.exception.RestException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Map;


@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public Map<String, String> signIn(UserDto.SignIn signInDto) throws IOException {
        Connection.Response res = Jsoup.connect("https://ecampus.smu.ac.kr/login/index.php")
                .data("username", signInDto.getUsername(), "password", signInDto.getPassword())
                .method(Connection.Method.POST)
                .execute();
        if (!res.url().toString().equals("https://ecampus.smu.ac.kr/"))
            return null;
        return res.cookies();
    }

    @Override
    public AuthDto getInformation(Map<String, String> cookies) throws IOException {
        if (cookies == null)
            throw new RestException(HttpStatus.UNAUTHORIZED);
        Document doc = Jsoup.connect("https://ecampus.smu.ac.kr/user/user_edit.php")
                .cookies(cookies)
                .method(Connection.Method.GET)
                .get();
        return AuthDto.of(getById(doc, "id_firstname"), changeName(getById(doc, "id_department")), getById(doc, "id_email"));
    }

    public String getById(Document doc, String id) {
        return doc.select("input[id="+id+"]").val();
    }

    public String changeName(String department) {
        Map<String, String> map = Map.of(
                "융합전자공학전공", "지능IOT융합전공",
                "지능·데이터융합학부", "핀테크전공"
        );
        if (map.containsKey(department))
            return map.get(department);
        return department;
    }
}
