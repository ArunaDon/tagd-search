package com.tagdsearch.demo.elastic.query.client.service.impl;

import com.tagdsearch.demo.config.ElasticConfigData;
import com.tagdsearch.demo.config.ElasticQueryConfigData;
import com.tagdsearch.demo.elastic.model.index.impl.ContractIndexModel;
import com.tagdsearch.demo.elastic.query.client.exception.ElasticQueryClientException;
import com.tagdsearch.demo.elastic.query.client.service.ElasticQueryClient;
import com.tagdsearch.demo.elastic.query.client.util.ElasticQueryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractElasticQueryClient implements ElasticQueryClient<ContractIndexModel> {

    private static final Logger LOG = LoggerFactory.getLogger(ContractElasticQueryClient.class);

    private final ElasticConfigData elasticConfigData;

    private final ElasticQueryConfigData elasticQueryConfigData;

    private final ElasticsearchOperations elasticsearchOperations;

    private final ElasticQueryUtil<ContractIndexModel> elasticQueryUtil;

    public ContractElasticQueryClient(ElasticConfigData configData,
                                      ElasticQueryConfigData queryConfigData,
                                      ElasticsearchOperations elasticOperations,
                                      ElasticQueryUtil<ContractIndexModel> queryUtil) {

        this.elasticConfigData = configData;
        this.elasticQueryConfigData = queryConfigData;
        this.elasticsearchOperations = elasticOperations;
        this.elasticQueryUtil = queryUtil;
    }





    @Override
    public ContractIndexModel getIndexModelById(String id) {
        Query query = elasticQueryUtil.getSearchQueryById(id);
       SearchHit<ContractIndexModel> searchResult = elasticsearchOperations.searchOne(query,ContractIndexModel.class,
                IndexCoordinates.of(elasticConfigData.getIndexName()));
       if (searchResult == null){
           LOG.error("No Document found at elasticsearch with id"+id);
           throw new ElasticQueryClientException("No Document found at elasticsearch with id"+id);
       }
       LOG.info("Document with id {} retrieved successfully",searchResult.getId());
        return searchResult.getContent();
    }

    @Override
    public List<ContractIndexModel> getIndexModelByText(String text) {
        Query query = elasticQueryUtil.getSearchQueryByFieldText(elasticQueryConfigData.getTextField(), text);
       SearchHits<ContractIndexModel> searchResult = elasticsearchOperations.search(query,ContractIndexModel.class,
                IndexCoordinates.of(elasticConfigData.getIndexName()));
       LOG.info("{} of documents with text {} retrieved successfully",searchResult.getTotalHits(),text);
        return searchResult.get().map(SearchHit::getContent).collect(Collectors.toList());
    }

    @Override
    public List<ContractIndexModel> getAllIndexModels() {
        Query query = elasticQueryUtil.getSearchQueryForAll();
        SearchHits<ContractIndexModel> searchResult = elasticsearchOperations.search(query,ContractIndexModel.class,
                IndexCoordinates.of(elasticConfigData.getIndexName()));
        LOG.info("{} number of documents retrieved successfully",searchResult.getTotalHits());
        return searchResult.get().map(SearchHit::getContent).collect(Collectors.toList());
    }
}
