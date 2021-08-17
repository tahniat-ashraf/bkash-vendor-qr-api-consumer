package com.priyam.vendorqrapitester;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Tahniat Ashraf Priyam
 * @since 17/8/21
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetRecentTransactionResponse {

    private List<Transaction> transactions;

}
