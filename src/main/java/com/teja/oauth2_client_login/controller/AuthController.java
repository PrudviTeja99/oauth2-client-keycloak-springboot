package com.teja.oauth2_client_login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    OAuth2AuthorizedClientService authorizedClientService;
    @GetMapping("/authenticate")
    public ResponseEntity<String> success(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient oAuth2AuthorizedClient = authorizedClientService.loadAuthorizedClient(oauth2AuthenticationToken.getAuthorizedClientRegistrationId(),oauth2AuthenticationToken.getName());
        String accessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
        MultiValueMap<String,String> headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+accessToken);
        return new ResponseEntity<>( "Successfully Authenticated",headers , HttpStatus.OK);
    }
}
