package com.scaler.ecommerceuserservice;

import com.scaler.ecommerceuserservice.security.services.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@SpringBootTest
class EcommerceUserServiceApplicationTests {

    @Autowired
    private JpaRegisteredClientRepository jpaRegisteredClientRepository;

    @Test
    void contextLoads() {
    }

    /*@Test
    public void addSampleRegisteredClient(){
        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("oidc-client")
                .clientSecret("$2a$12$aIMlcj6.2yWm438hiaS28uxfE3a2jeo1b7ZFj4QrOx3tNap2iHqIG")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                .postLogoutRedirectUri("http://127.0.0.1:8080/")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .scope("ADMIN")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();

        jpaRegisteredClientRepository.save(oidcClient);
    }*/
}
