package com.priyam.vendorqrapitester;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tahniat Ashraf Priyam
 * @since 17/8/21
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private String terminalId;
    private String merchantWallet;
    private String transactionId;
    private String amount;
    private String timestamp;
    private String customerWallet;
}
