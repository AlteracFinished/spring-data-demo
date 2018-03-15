package org.alterac.springdemo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultItem<T> {
    private Boolean success;
    private String message;
    private T data;
}
