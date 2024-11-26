package com.tagdsearch.demo.contract.search.service.service.impl;

import com.tagdsearch.demo.contract.search.service.model.ElasticQueryServiceResponseModel;
import com.tagdsearch.demo.contract.search.service.service.ElasticQueryService;
import com.tagdsearch.demo.contract.search.service.transformer.ElasticResponseModelTransformer;
import com.tagdsearch.demo.elastic.model.index.impl.ContractIndexModel;
import com.tagdsearch.demo.elastic.query.client.service.ElasticQueryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractElasticQueryService implements ElasticQueryService {

    private static final Logger LOG = LoggerFactory.getLogger(ContractElasticQueryService.class);

    private final ElasticResponseModelTransformer elasticResponseModelTransformer;


    private final ElasticQueryClient<ContractIndexModel> elasticQueryClient;

    public ContractElasticQueryService(ElasticResponseModelTransformer transformer, ElasticQueryClient<ContractIndexModel> queryClient) {
        this.elasticResponseModelTransformer = transformer;
        this.elasticQueryClient = queryClient;
    }


    @Override
    public ElasticQueryServiceResponseModel getDocumentById(String id) {
        LOG.info("Querying elasticsearch by id {}",id);
        return elasticResponseModelTransformer.getResponseModel(elasticQueryClient.getIndexModelById(id));
    }

    @Override
    public List<ElasticQueryServiceResponseModel> getDocumetByText(String text) {
        LOG.info("Querying elasticsearch by text {}",text);
        return elasticResponseModelTransformer.getResponseModdels(elasticQueryClient.getIndexModelByText(text));
    }

    @Override
    public List<ElasticQueryServiceResponseModel> getAllDocuments() {
        LOG.info("Querying documents in elasticsearch");
        return elasticResponseModelTransformer.getResponseModdels(elasticQueryClient.getAllIndexModels());
    }
}
