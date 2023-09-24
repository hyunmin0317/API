package com.smunity.api.domain.account.service.impl;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.account.dto.InformationDto;
import com.smunity.api.domain.account.service.EcampusService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.Map;


@Service
public class EcampusServiceImpl implements EcampusService {

    @Override
    public Map<String, String> signIn(String username, String password) throws IOException {
        Connection.Response res = Jsoup.connect("https://ecampus.smu.ac.kr/login/index.php")
                .data("username", username, "password", password)
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
        if (cookies != null) {
            Document doc = Jsoup.connect("https://ecampus.smu.ac.kr/user/user_edit.php")
                    .cookies(cookies)
                    .method(Connection.Method.GET)
                    .get();
            InformationDto informationDto = InformationDto.builder()
                    .username(getInformationById(doc, "id_firstname"))
                    .department(getInformationById(doc, "id_department"))
                    .email(getInformationById(doc, "id_email"))
                    .build();
            return informationDto;
        }
        return null;
    }

    public String getInformationById(Document doc, String id) {
        return doc.select("input[id="+id+"]").val();
    }
}
