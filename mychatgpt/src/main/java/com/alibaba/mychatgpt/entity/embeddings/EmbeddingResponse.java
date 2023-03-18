package com.alibaba.mychatgpt.entity.embeddings;

import com.alibaba.mychatgpt.entity.common.Usage;

import java.util.List;

import lombok.Data;

/**
 * 描述：
 *
 * @author https:www.unfbx.com
 *  2023-02-15
 */
@Data
public class EmbeddingResponse {

    private String object;
    private List<Item> data;
    private String model;
    private Usage usage;
}
