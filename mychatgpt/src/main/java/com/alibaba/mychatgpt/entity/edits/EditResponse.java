package com.alibaba.mychatgpt.entity.edits;

import com.alibaba.mychatgpt.entity.common.Choice;
import com.alibaba.mychatgpt.entity.common.Usage;
import lombok.Data;
/**
 * 描述：
 *
 * @author https:www.unfbx.com
 *  2023-02-15
 */
@Data
public class EditResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private Choice[] choices;
    private Usage usage;
}
