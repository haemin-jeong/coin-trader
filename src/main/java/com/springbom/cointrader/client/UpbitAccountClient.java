package com.springbom.cointrader.client;

import com.springbom.cointrader.client.dto.UpbitAccountResponse;
import com.springbom.cointrader.util.UpbitTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UpbitAccountClient {

    private final RestTemplate restTemplate;
    private final UpbitTokenGenerator tokenGenerator;

    public List<UpbitAccountResponse> getMyAccounts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set("Authorization", tokenGenerator.generate());

        HttpEntity<Object> entity = new HttpEntity<>(headers);
        URI uri = URI.create("https://api.upbit.com/v1/accounts");

        return restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<UpbitAccountResponse>>() {}).getBody();
    }
}
