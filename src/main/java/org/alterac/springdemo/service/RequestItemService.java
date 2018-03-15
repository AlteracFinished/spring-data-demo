package org.alterac.springdemo.service;


import org.alterac.springdemo.entity.RequestItem;
import org.alterac.springdemo.es.dao.EsRequestItemRepository;
import org.alterac.springdemo.redis.dao.RedisRequestItemRepository;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryShardContext;
import org.elasticsearch.search.rescore.RescoreBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
public class RequestItemService {
    @Autowired
    private EsRequestItemRepository esRequestItemRepository;

    @Autowired
    private RedisRequestItemRepository redisRequestItemRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public void saveRequestItem(RequestItem requestItem) {
        esRequestItemRepository.save(requestItem);
        redisRequestItemRepository.save(requestItem);
    }

    public Optional<RequestItem> getRequestItemByName(String name) {
        return redisRequestItemRepository
                .findById(name);
    }

    public void removeRequestItem(String name) {
        redisRequestItemRepository.deleteById(name);
        esRequestItemRepository.deleteById(name);
    }

    public Page<RequestItem> getRequestItemByContent(String content, Pageable pageable) {
        QueryBuilder queryBuilder = boolQuery()
                .must(boolQuery()
                        .should(matchQuery("content", content)));
        //esRequestItemRepository.search()
        return esRequestItemRepository.search(queryBuilder, pageable);
    }
}
