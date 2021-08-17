package com.priyam.vendorqrapitester;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Tahniat Ashraf Priyam
 * @since 17/8/21
 */

@RestController
@RequestMapping("/bkash-vendor-qr-consumer/api")
@RequiredArgsConstructor
public class BkashVendorQrApiConsumerController {

    private final BkashVendorQrApiConsumerService consumerService;


    @GetMapping("/getRecentTransactions/{merchantId}/{terminalId}/{customerWallet}")
    public Mono<GetRecentTransactionResponse> getRecentTransactionResponseMono(
            @PathVariable(name = "merchantId") String merchantId,
            @PathVariable(name = "terminalId") String terminalId,
            @PathVariable(name = "customerWallet") String customerWallet
    ) {
        return consumerService.getRecentTransaction(merchantId, terminalId, customerWallet);
    }

}
