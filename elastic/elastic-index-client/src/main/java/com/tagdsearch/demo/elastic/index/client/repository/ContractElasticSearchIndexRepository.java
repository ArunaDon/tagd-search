package com.tagdsearch.demo.elastic.index.client.repository;

import com.tagdsearch.demo.elastic.model.index.impl.ContractIndexModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractElasticSearchIndexRepository extends ElasticsearchRepository<ContractIndexModel, String> {
}
