package com.yeamy.sqlite.ds.demo;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

public class Man {
    public Info info;
    public BigDecimal money;

    @NonNull
    @Override
    public String toString() {
        return "Man{" +
                "info=" + info +
                ", money=" + money +
                '}';
    }
}
