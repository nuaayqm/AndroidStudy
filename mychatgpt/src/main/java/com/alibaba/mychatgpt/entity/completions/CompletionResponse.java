package com.alibaba.mychatgpt.entity.completions;

import com.alibaba.mychatgpt.entity.common.OpenAiResponse;
import com.alibaba.mychatgpt.entity.common.Choice;
import com.alibaba.mychatgpt.entity.common.Usage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompletionResponse extends OpenAiResponse{
    private String id;
    private String object;
    private long created;
    private String model;
    private Choice[] choices;
    private Usage usage;
}
