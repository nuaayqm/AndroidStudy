package com.alibaba.mychatgpt.entity.common;

import lombok.Data;

import java.util.List;
@Data
public class OpenAiResponse<T> {
    private String object;
    private List<T> data;
    private Error error;


    @Data
    public class Error {
        private String message;
        private String type;
        private String param;
        private String code;
    }
}

