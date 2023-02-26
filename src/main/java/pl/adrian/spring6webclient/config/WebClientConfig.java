package pl.adrian.spring6webclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig implements WebClientCustomizer {

    private final String ROOT_URL;

    private final ReactiveOAuth2AuthorizedClientManager authorizedClientManager;

    public WebClientConfig(@Value("${webclient.root-url}") String rootUrl,
                           ReactiveOAuth2AuthorizedClientManager authorizedClientManager) {
        this.ROOT_URL = rootUrl;
        this.authorizedClientManager = authorizedClientManager;
    }

    @Override
    public void customize(WebClient.Builder webClientBuilder) {
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauthFilterFunction =
                new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);

        oauthFilterFunction.setDefaultClientRegistrationId("springauth");

        webClientBuilder
                .filter(oauthFilterFunction)
                .baseUrl(ROOT_URL);
    }
}
