package com.smunity.api.domain.account.service.impl;

import com.smunity.api.domain.account.service.EcampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;


@Service
public class EcampusServiceImpl implements EcampusService {

    @Override
    public String signIn(String username, String password) throws RuntimeException {
        WebClient webClient = WebClient.create("https://ecampus.smu.ac.kr");

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", username);
        formData.add("password", password);
        System.out.println(formData);
        return webClient.post()
                .uri("/login/index.php")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void getInformation(String username, String password) {
        System.out.println(username);
    }
}
