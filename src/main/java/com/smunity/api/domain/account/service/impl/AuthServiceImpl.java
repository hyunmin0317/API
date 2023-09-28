package com.smunity.api.domain.account.service.impl;

import com.smunity.api.domain.account.dto.InformationDto;
import com.smunity.api.domain.account.dto.SignInDto;
import com.smunity.api.domain.account.service.AuthService;
import com.smunity.api.global.exception.CustomException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URL;
import java.util.Map;


@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public Map<String, String> signIn(SignInDto signInDto) throws IOException {
        Connection.Response res = Jsoup.connect("https://ecampus.smu.ac.kr/login/index.php")
                .data("username", signInDto.getUsername(), "password", signInDto.getPassword())
                .method(Connection.Method.POST)
                .execute();
        URL url = res.url();
        if (url.toString().equals("https://ecampus.smu.ac.kr/")) {
            return res.cookies();
        }
        return null;
    }

    @Override
    public InformationDto getInformation(Map<String, String> cookies) throws IOException {
        if (cookies == null)
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        Document doc = Jsoup.connect("https://ecampus.smu.ac.kr/user/user_edit.php")
                .cookies(cookies)
                .method(Connection.Method.GET)
                .get();
        InformationDto informationDto = InformationDto.builder()
                .username(getInformationById(doc, "id_firstname"))
                .department(changeDepartmentName(getInformationById(doc, "id_department")))
                .email(getInformationById(doc, "id_email"))
                .build();
        return informationDto;
    }

    public String getInformationById(Document doc, String id) {
        return doc.select("input[id="+id+"]").val();
    }

    public String changeDepartmentName(String department) {
        String dept_name = department;
        Map<String, String> map = Map.of(
                "융합전자공학전공", "지능IOT융합전공",
                "지능·데이터융합학부", "핀테크전공"
        );
        if (map.containsKey(dept_name))
            dept_name = map.get(dept_name);
        return dept_name;
    }
}
