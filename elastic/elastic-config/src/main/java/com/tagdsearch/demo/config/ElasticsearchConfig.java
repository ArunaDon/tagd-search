package com.tagdsearch.demo.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.tagdsearch.demo.elastic")
public class ElasticsearchConfig {

    private final ElasticConfigData elasticConfigData;

    public ElasticsearchConfig(ElasticConfigData configData) {
        this.elasticConfigData = configData;
    }

    @Bean
    public RestClient elasticRestClient() {
        UriComponents serverUri = UriComponentsBuilder.fromHttpUrl(elasticConfigData.getConnectionUrl()).build();
        return RestClient.builder(new HttpHost(
                Objects.requireNonNull(serverUri.getHost()),
                serverUri.getPort(),
                serverUri.getScheme()
        )).setRequestConfigCallback(requestConfigBuilder ->
                requestConfigBuilder
                        .setConnectTimeout(elasticConfigData.getConnectionTimeout())
                        .setSocketTimeout(elasticConfigData.getSocketTimeout())
        ).build();
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() {

        ElasticsearchClient elasticsearchClient = new ElasticsearchClient(
                new RestClientTransport(
                        elasticRestClient(),
                        new JacksonJsonpMapper()
                )
        );

        return new ElasticsearchTemplate(elasticsearchClient);
    }
}
