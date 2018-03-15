package org.alterac.springdemo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash("RequestItem")
@Document(indexName = "request-item", type = "requestItem")
@Mapping(mappingPath = "es/mapping/requestItem-mapping.json")
@Setting(settingPath = "es/setting/demo-setting.json")
@NoArgsConstructor
@AllArgsConstructor
public class RequestItem {
    @Id
    private String name;
    private String content;
}
