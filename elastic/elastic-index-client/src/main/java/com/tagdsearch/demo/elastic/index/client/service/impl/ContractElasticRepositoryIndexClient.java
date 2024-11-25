package com.tagdsearch.demo.elastic.index.client.service.impl;

import com.tagdsearch.demo.elastic.index.client.repository.ContractElasticSearchIndexRepository;
import com.tagdsearch.demo.elastic.index.client.service.ElasticIndexClient;
import com.tagdsearch.demo.elastic.model.index.impl.ContractIndexModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service
public class ContractElasticRepositoryIndexClient implements ElasticIndexClient<ContractIndexModel> {
    private static final Logger LOG = LoggerFactory.getLogger(ContractElasticRepositoryIndexClient .class);

    private final ContractElasticSearchIndexRepository contractElasticsearchIndexRepository;

    public ContractElasticRepositoryIndexClient(ContractElasticSearchIndexRepository  indexRepository) {
        this.contractElasticsearchIndexRepository = indexRepository;
    }

    @Override
    public List<String> save(List<ContractIndexModel> documents) {
        List<ContractIndexModel> repositoryResponse =
                (List<ContractIndexModel>) contractElasticsearchIndexRepository.saveAll(documents);
        List<String> ids = repositoryResponse.stream().map(ContractIndexModel::getId).collect(Collectors.toList());
        LOG.info("Documents indexed successfully with type: {} and ids: {}", ContractIndexModel.class.getName(), ids);
        return ids;
    }
}
