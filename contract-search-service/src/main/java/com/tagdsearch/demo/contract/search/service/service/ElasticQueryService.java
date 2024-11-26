package com.tagdsearch.demo.contract.search.service.service;

import com.tagdsearch.demo.contract.search.service.model.ElasticQueryServiceResponseModel;

import java.util.List;

public interface ElasticQueryService {
    ElasticQueryServiceResponseModel getDocumentById(String id);
    List<ElasticQueryServiceResponseModel> getDocumetByText(String text);
    List<ElasticQueryServiceResponseModel> getAllDocuments();
}
