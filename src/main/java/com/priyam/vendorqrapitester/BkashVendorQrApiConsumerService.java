package com.priyam.vendorqrapitester;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Tahniat Ashraf Priyam
 * @since 17/8/21
 */

@Service
@Slf4j
public class BkashVendorQrApiConsumerService {

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

    @Value("${getTokenURL}")
    private String getTokenURL;

    @Value("${getRecentTransactionUrl}")
    private String getRecentTransactionUrl;

    @Value("${apiKey}")
    private String apiKey;

    private final WebClient webClient;

    public BkashVendorQrApiConsumerService() {
        this.webClient = WebClient.create();
    }

    private Mono<String> getAccessToken() {

        var authHeader = "Basic " + Base64Utils.encodeToString((clientId + ":" + clientSecret).getBytes(UTF_8));

        return webClient
                .post()
                .uri(getTokenURL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", authHeader)
                .bodyValue("grant_type=client_credentials")
                .retrieve()
                .bodyToMono(GetTokenResponse.class)
                .map(GetTokenResponse::getAccessToken);

    }

    public Mono<GetRecentTransactionResponse> getRecentTransaction(String merchantId, String terminalId, String customerWallet) {

        var url = getRecentTransactionUrl + "/" + merchantId + "/" + terminalId + "/" + customerWallet;

        return getAccessToken()
                .flatMap(accessToken -> {
                            log.info("accessToken :: " + accessToken);
                            var authToken = "Bearer " + accessToken;
                            return webClient
                                    .get()
                                    .uri(url)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .header("X-API-Key", apiKey)
                                    .header("Authorization", authToken)
                                    .retrieve()
                                    .bodyToMono(GetRecentTransactionResponse.class);
                        }
                );

    }
}
