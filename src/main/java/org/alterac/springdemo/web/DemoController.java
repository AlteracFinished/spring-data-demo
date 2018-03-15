package org.alterac.springdemo.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.alterac.springdemo.entity.RequestItem;
import org.alterac.springdemo.entity.ResultItem;
import org.alterac.springdemo.service.RequestItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private RequestItemService requestItemService;

    @GetMapping("/request")
    public ResultItem request() {
        return ResultItem.builder()
                .success(true)
                .message("mook sample")
                .build();
    }

    @PostMapping("/request/save")
    public ResultItem requestSave(@RequestBody RequestItem request) {
        if (StringUtils.isEmpty(request.getName()) || StringUtils.isEmpty(request.getContent())) {
            return ResultItem.builder()
                    .success(false)
                    .message("name or content empty")
                    .build();
        }
        requestItemService.saveRequestItem(request);
        return ResultItem.builder()
                .success(true)
                .message("success")
                .build();
    }

    @PostMapping("/request/get")
    public ResultItem requestGet(@RequestBody RequestItem request) {
        if (StringUtils.isEmpty(request.getName())) {
            return ResultItem.builder()
                    .success(false)
                    .message("name empty")
                    .build();
        }
        RequestItem item = requestItemService.getRequestItemByName(request.getName()).orElse(null);
        return ResultItem.builder()
                .success(true)
                .data(item)
                .build();
    }

    @PostMapping("/request/search")
    public ResultItem requestSearch(@RequestBody RequestItem request) {
        if (request.getContent() == null) {
            return ResultItem.builder()
                    .success(false)
                    .message("content empty")
                    .build();
        }
        Page<RequestItem> item = requestItemService.getRequestItemByContent(request.getContent(), Pageable.unpaged());
        return ResultItem.builder()
                .success(true)
                .data(item)
                .build();
    }

    @PostMapping("/request/remove")
    public ResultItem requestRemove(@RequestBody RequestItem request) {
        if (request.getName() == null) {
            return ResultItem.builder()
                    .success(false)
                    .message("name empty")
                    .build();
        }
        requestItemService.removeRequestItem(request.getName());
        return ResultItem.builder()
                .success(true)
                .message("success")
                .build();
    }
}
