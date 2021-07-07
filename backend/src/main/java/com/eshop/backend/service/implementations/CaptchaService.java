package com.eshop.backend.service.implementations;

import com.eshop.backend.dto.RecaptchaResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CaptchaService {

    private final RestTemplate restTemplate;

    @Value("${google.recaptcha.secret.key}")
    public String recaptchaSecret;
    @Value("${google.recaptcha.verify.url}")
    public String recaptchaVerifyUrl;
    @Value("${spring.google.recaptcha.default.key}")
    public String recaptchaDefaultKey;

    public CaptchaService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public boolean verify(String response) {

        if (response.equals("")) return true;

        MultiValueMap<Object, Object> param = new LinkedMultiValueMap<>();
        param.add("secret", recaptchaSecret);
        param.add("response", response);

        RecaptchaResponseDTO recaptchaResponse = null;
        try {
            recaptchaResponse = this.restTemplate.postForObject(recaptchaVerifyUrl, param, RecaptchaResponseDTO.class);
        } catch (RestClientException e) {
            System.out.print(e.getMessage());
        }

        return recaptchaResponse != null && recaptchaResponse.isSuccess();
    }

}
