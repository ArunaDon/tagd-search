package com.tagdsearch.demo.contract.search.service.model.assembler;

import com.tagdsearch.demo.contract.search.service.api.ElasticDocumentController;
import com.tagdsearch.demo.contract.search.service.model.ElasticQueryServiceResponseModel;
import com.tagdsearch.demo.contract.search.service.transformer.ElasticResponseModelTransformer;
import com.tagdsearch.demo.elastic.model.index.impl.ContractIndexModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ElasticQueryServiceResponseModelAssembler
        extends RepresentationModelAssemblerSupport<ContractIndexModel, ElasticQueryServiceResponseModel> {


    private final ElasticResponseModelTransformer elasticResponseModelTransformer;

    public ElasticQueryServiceResponseModelAssembler(ElasticResponseModelTransformer transformer){
        super(ElasticDocumentController.class,ElasticQueryServiceResponseModel.class);
        this.elasticResponseModelTransformer = transformer;
    }

    @Override
    public ElasticQueryServiceResponseModel toModel(ContractIndexModel contractIndexModel) {
        ElasticQueryServiceResponseModel responseModel =
                elasticResponseModelTransformer.getResponseModel(contractIndexModel);
        responseModel.add(
                linkTo(methodOn(ElasticDocumentController.class)
                        .getDocumentById((contractIndexModel.getId())))
                        .withSelfRel());
        responseModel.add(
                linkTo(ElasticDocumentController.class)
                        .withRel("documents"));
        return responseModel;
    }

    public List<ElasticQueryServiceResponseModel> toModels(List<ContractIndexModel> contractIndexModels) {
        return contractIndexModels.stream().map(this::toModel).collect(Collectors.toList());
    }
}
