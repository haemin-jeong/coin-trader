package com.springbom.cointrader.client;

import com.springbom.cointrader.client.dto.UpbitAccountResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class UpbitAccountClientTest {

    @Autowired
    UpbitAccountClient client;

    @Test
    @DisplayName("내가 보유한 자신 리스트 불러와서 출력하기")
    void geyMyAccountsTest() {
        List<UpbitAccountResponse> myAccounts = client.getMyAccounts();
        System.out.println("myAccounts = " + myAccounts);
    }
}
