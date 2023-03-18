package com.alibaba.mychatgpt.entity.embeddings;


import android.util.Log;

import com.alibaba.mychatgpt.exception.BaseException;
import com.alibaba.mychatgpt.exception.CommonError;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述：
 *
 * @author https:www.unfbx.com
 *  2023-02-15
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Embedding {
    private final String TAG = "com.alibaba.mychatgpt.entity.embeddings.Embedding";
    @NonNull
    @Builder.Default
    private String model = Model.TEXT_EMBEDDING_ADA_002.getName();
    /**
     * 必选项：长度不能超过：8192
     */
    @NonNull
    private String input;

    private String user;

    public void setModel(Model model) {
        if (Objects.isNull(model)) {
            model = Model.TEXT_EMBEDDING_ADA_002;
        }
        this.model = model.getName();
    }

    public void setInput(String input) {
        if (input == null || "".equals(input)) {
            Log.e(TAG, "input不能为空");
            throw new BaseException(CommonError.PARAM_ERROR);
        }
        if (input.length() > 8192) {
            Log.e(TAG,"input超长");
            throw new BaseException(CommonError.PARAM_ERROR);
        }
        this.input = input;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Getter
    @AllArgsConstructor
    public enum Model {
        TEXT_EMBEDDING_ADA_002("text-embedding-ada-002"),
        ;
        private String name;
    }
}
