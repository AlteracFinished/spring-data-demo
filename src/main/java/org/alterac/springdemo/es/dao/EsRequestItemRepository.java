package org.alterac.springdemo.es.dao;

import org.alterac.springdemo.entity.RequestItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsRequestItemRepository extends ElasticsearchRepository<RequestItem,String> {
}
