package com.teja.oauth2_client_login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    @GetMapping("/test")
    public String getToken(OAuth2AuthenticationToken authentication){
        if(authentication==null){
            return "Unauthorized";
        }
        OAuth2AuthorizedClient authorizedClient =
                this.authorizedClientService.loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName());
        if (authorizedClient == null) {
            return "No authorized client found";
        }

        return authorizedClient.getAccessToken().getTokenValue();
    }
}
