package org.alterac.springdemo.redis.dao;

import org.alterac.springdemo.entity.RequestItem;
import org.springframework.data.repository.CrudRepository;

public interface RedisRequestItemRepository extends CrudRepository<RequestItem,String> {
}
