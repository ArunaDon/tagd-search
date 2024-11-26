package com.tagdsearch.demo.contract.search.service.transformer;

import com.tagdsearch.demo.contract.search.service.model.ElasticQueryServiceResponseModel;
import com.tagdsearch.demo.elastic.model.index.impl.ContractIndexModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ElasticResponseModelTransformer {

    public ElasticQueryServiceResponseModel getResponseModel(ContractIndexModel contractIndexModel){
        return ElasticQueryServiceResponseModel
                .builder()
                .id(contractIndexModel.getId())
                .title(contractIndexModel.getTitle())
                .content(contractIndexModel.getContent())
                .createAt(contractIndexModel.getCreateAt())
                .build();
    }

    public List<ElasticQueryServiceResponseModel> getResponseModdels(List<ContractIndexModel> contractIndexModels){
        return contractIndexModels.stream().map(this::getResponseModel).collect(Collectors.toList());
    }

}
