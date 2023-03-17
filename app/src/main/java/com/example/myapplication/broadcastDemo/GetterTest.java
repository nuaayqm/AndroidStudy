package com.example.myapplication.broadcastDemo;

import lombok.Getter;

@Getter
public class GetterTest {
    private int a;
    private int a1;
    private int a2;

    public GetterTest() {
    }

    public GetterTest(int a) {
        this.a = a;
    }
}
