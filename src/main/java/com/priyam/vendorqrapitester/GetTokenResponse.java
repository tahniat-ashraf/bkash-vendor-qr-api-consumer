package com.priyam.vendorqrapitester;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class GetTokenResponse {


    @JsonProperty(value = "access_token")
    private String accessToken;
    @JsonProperty(value = "expires_in")
    private Long expiresIn;
    @JsonProperty(value = "token_type")
    private String tokenType;
}
