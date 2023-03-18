package com.alibaba.mychatgpt.exception;

public interface IError {
    String msg();

    int code();
}