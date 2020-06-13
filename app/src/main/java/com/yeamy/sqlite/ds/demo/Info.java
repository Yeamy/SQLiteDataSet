package com.yeamy.sqlite.ds.demo;

import androidx.annotation.NonNull;

public class Info {
    public String name;
    public IDD idd;

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", idd=" + idd +
                '}';
    }
}