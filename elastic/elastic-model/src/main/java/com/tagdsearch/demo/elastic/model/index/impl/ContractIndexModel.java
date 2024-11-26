package com.tagdsearch.demo.elastic.model.index.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tagdsearch.demo.elastic.model.index.IndexModel;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document(indexName = "contract-index")
public class ContractIndexModel implements IndexModel {

    @JsonProperty
    private String id;
    @JsonProperty
    private String title;
    @JsonProperty
    private String content;
    @JsonProperty
    private List<String> permissions;

    @Field(type = FieldType.Date, pattern = "uuuu-MM-dd'T'HH:mm:ssZZ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd'T'HH:mm:ssZZ")
    @JsonProperty
    private LocalDateTime createAt;

}
