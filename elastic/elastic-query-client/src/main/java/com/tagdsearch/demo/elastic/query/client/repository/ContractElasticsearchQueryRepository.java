package com.tagdsearch.demo.elastic.query.client.repository;

import com.tagdsearch.demo.elastic.model.index.impl.ContractIndexModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractElasticsearchQueryRepository extends ElasticsearchRepository<ContractIndexModel, String> {
    List<ContractIndexModel> findByText(String text);
}
