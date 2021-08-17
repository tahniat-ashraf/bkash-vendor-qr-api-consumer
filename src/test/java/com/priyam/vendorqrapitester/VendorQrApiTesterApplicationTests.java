package com.priyam.vendorqrapitester;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@Import(value = {BkashVendorQrApiConsumerController.class, BkashVendorQrApiConsumerService.class})
@ActiveProfiles("dev")
class VendorQrApiTesterApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getRecentTransaction() {


        webTestClient
                .get()
                .uri("http://localhost:6660/bkash-vendor-qr-consumer/api/getRecentTransactions/01854580120/Agora/01765614227")
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("@.transactions.[0].transactionId").isNotEmpty();

    }

}
