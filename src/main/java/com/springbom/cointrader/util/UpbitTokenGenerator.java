package com.springbom.cointrader.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.springbom.cointrader.config.SlackKeyProperties;
import com.springbom.cointrader.config.UpbitKeyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpbitTokenGenerator {

    private final UpbitKeyProperties properties;

    public String generate() {
        Algorithm algorithm = Algorithm.HMAC256(properties.getSecretKey());
        String jwtToken = JWT.create()
                .withClaim("access_key", properties.getAccessKey())
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);

        return "Bearer " + jwtToken;
    }

    @Component
    @RequiredArgsConstructor
    public static class SlackTokenGenerator {

        private final SlackKeyProperties properties;

        public String generate() {
            return "Bearer " + properties.getKey();
        }
    }
}
