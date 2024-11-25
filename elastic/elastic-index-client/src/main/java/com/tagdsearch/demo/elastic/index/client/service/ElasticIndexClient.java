package com.tagdsearch.demo.elastic.index.client.service;

import com.tagdsearch.demo.elastic.model.index.IndexModel;

import java.util.List;

public interface ElasticIndexClient<T extends IndexModel> {
    List<String> save(List<T> documents);
}
