package com.tagdsearch.demo.elastic.query.client.service.impl;

import com.tagdsearch.demo.common.util.CollectionsUtil;
import com.tagdsearch.demo.elastic.model.index.impl.ContractIndexModel;
import com.tagdsearch.demo.elastic.query.client.exception.ElasticQueryClientException;
import com.tagdsearch.demo.elastic.query.client.repository.ContractElasticsearchQueryRepository;
import com.tagdsearch.demo.elastic.query.client.service.ElasticQueryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class ContractElasticRepositoryQueryClient implements ElasticQueryClient<ContractIndexModel> {

    private static final Logger LOG = LoggerFactory.getLogger(ContractElasticRepositoryQueryClient.class);
    private final ContractElasticsearchQueryRepository contractElasticsearchQueryRepository;


    public ContractElasticRepositoryQueryClient(ContractElasticsearchQueryRepository repository) {
        this.contractElasticsearchQueryRepository = repository;
    }

    @Override
    public ContractIndexModel getIndexModelById(String id) {

        Optional<ContractIndexModel> searchResult = contractElasticsearchQueryRepository.findById(id);
        LOG.info("Document with id {} retrieved successfullyl",
                searchResult.orElseThrow(()->
                        new ElasticQueryClientException("No document found at elasticsearch with id"+id)).getId());
        return searchResult.get();
    }

    @Override
    public List<ContractIndexModel> getIndexModelByText(String text) {
        List<ContractIndexModel> searchResult = contractElasticsearchQueryRepository.findByText(text);
        LOG.info("Document with id {} retrieved successfullyl",searchResult.size(),text);
        return searchResult;
    }

    @Override
    public List<ContractIndexModel> getAllIndexModels() {
        Iterable<ContractIndexModel> all = contractElasticsearchQueryRepository.findAll();

        List<ContractIndexModel> searchResult = CollectionsUtil.getInstance().getListFromIterable(
                contractElasticsearchQueryRepository.findAll()
        );
        LOG.info("{} number of documents retrieved successfully",searchResult.size());
        return searchResult;
    }
}
