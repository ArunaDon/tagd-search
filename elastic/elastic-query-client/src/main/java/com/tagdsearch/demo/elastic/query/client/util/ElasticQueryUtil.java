package com.tagdsearch.demo.elastic.query.client.util;

import com.tagdsearch.demo.elastic.model.index.IndexModel;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Component;

@Component
public class ElasticQueryUtil<T extends IndexModel> {

    // Query by document ID
    public Query getSearchQueryById(String id) {
        // Using StringQuery for ID-based searches
        return new StringQuery("{\"ids\": [\"" + id + "\"]}");
    }

    // Query by field and text
    public Query getSearchQueryByFieldText(String field, String text) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery(field, text));
        return new StringQuery(boolQuery.toString());
    }

    // Query to fetch all documents
    public Query getSearchQueryForAll() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchAllQuery());
        return new StringQuery(boolQuery.toString());
    }
}
