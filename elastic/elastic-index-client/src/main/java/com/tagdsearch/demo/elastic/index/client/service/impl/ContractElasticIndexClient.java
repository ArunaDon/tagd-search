package com.tagdsearch.demo.elastic.index.client.service.impl;

import com.tagdsearch.demo.config.ElasticConfigData;
import com.tagdsearch.demo.elastic.index.client.service.ElasticIndexClient;
import com.tagdsearch.demo.elastic.index.client.util.ElasticIndexUtil;
import com.tagdsearch.demo.elastic.model.index.impl.ContractIndexModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractElasticIndexClient implements ElasticIndexClient<ContractIndexModel> {

    private static final Logger LOG = LoggerFactory.getLogger(ContractElasticIndexClient.class);

    private final ElasticConfigData elasticConfigData;

    private final ElasticsearchOperations elasticsearchOperations;

    private final ElasticIndexUtil<ContractIndexModel> elasticIndexUtil;

    public ContractElasticIndexClient(ElasticConfigData configData,
                                     ElasticsearchOperations elasticOperations,
                                     ElasticIndexUtil<ContractIndexModel> indexUtil) {
        this.elasticConfigData = configData;
        this.elasticsearchOperations = elasticOperations;
        this.elasticIndexUtil = indexUtil;
    }

    @Override
    public List<String> save(List<ContractIndexModel> documents) {
        List<IndexQuery> indexQueries = elasticIndexUtil.getIndexQueries(documents);
        List<IndexedObjectInformation> indexedInfos = elasticsearchOperations.bulkIndex(
                indexQueries,
                IndexCoordinates.of(elasticConfigData.getIndexName())
        );

        List<String> documentIds = indexedInfos.stream()
                .map(info -> info.id())
                .collect(Collectors.toList());

        LOG.info("Documents indexed successfully with type: {} and ids: {}", ContractIndexModel.class.getName(), documentIds);
        return documentIds;
    }

}
