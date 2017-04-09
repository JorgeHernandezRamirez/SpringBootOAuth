package com.jorgehernandezramirez.spring.springboot.oauth.client.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableOAuth2Client
public class OAuthClientConfiguration {

    private static final String OAUTH_CLIENT = "oauth-client";

    private static final String OAUTH_LOGIN_CLIENT = "oauth-login-client";

    private static final String SECRET = "secret";

    private static final String OAUTH_TOKEN_URI = "http://localhost:8080/oauth/token";

    private static final String OAUTH_AUTHORIZATION_URI = "http://localhost:8080/oauth/authorize";

    private static final List<String> SCOPES = Arrays.asList("read");

    private static final String LOGINOK_URI = "/loginok";

    private static final String LOGINKO_URI = "/loginko";

    private static final String USERINFO_URI = "http://localhost:8080/user";

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    public OAuthClientConfiguration(){
        //Para Spring
    }

    @Bean
    public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
        return new OAuth2RestTemplate(buildResourceForOAuthClient(), oauth2ClientContext);
    }

    @Bean
    public Filter buildOAuth2Filter(){
        final OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationProcessingFilter = new OAuth2ClientAuthenticationProcessingFilter("/login");
        final OAuth2RestTemplate template = new OAuth2RestTemplate(buildResourceForOAuthLoginClient(), oauth2ClientContext);
        final UserInfoTokenServices tokenServices = new UserInfoTokenServices(USERINFO_URI, OAUTH_LOGIN_CLIENT);
        oAuth2ClientAuthenticationProcessingFilter.setRestTemplate(template);
        tokenServices.setRestTemplate(template);
        oAuth2ClientAuthenticationProcessingFilter.setTokenServices(tokenServices);
        oAuth2ClientAuthenticationProcessingFilter.setAuthenticationSuccessHandler(new ForwardAuthenticationSuccessHandler(LOGINOK_URI));
        oAuth2ClientAuthenticationProcessingFilter.setAuthenticationFailureHandler(new ForwardAuthenticationFailureHandler(LOGINKO_URI));
        return oAuth2ClientAuthenticationProcessingFilter;
    }

    private OAuth2ProtectedResourceDetails buildResourceDetails(final String clientId, final String secret,
                                                                       final String accessTokenUri, final String userAuthorizationUri,
                                                                       final List<String> scopes) {
        final AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
        resource.setClientId(clientId);
        resource.setClientSecret(secret);
        resource.setAccessTokenUri(accessTokenUri);
        resource.setUserAuthorizationUri(userAuthorizationUri);
        resource.setScope(scopes);
        return resource;
    }

    private OAuth2ProtectedResourceDetails buildResourceForOAuthClient(){
        return buildResourceDetails(OAUTH_CLIENT, SECRET, OAUTH_TOKEN_URI, OAUTH_AUTHORIZATION_URI,
                SCOPES);
    }

    private OAuth2ProtectedResourceDetails buildResourceForOAuthLoginClient(){
        return buildResourceDetails(OAUTH_LOGIN_CLIENT, SECRET, OAUTH_TOKEN_URI, OAUTH_AUTHORIZATION_URI,
                SCOPES);
    }
}
