package com.unimelb.swen90007.sda1_musicsystem.domain;

public enum Section {

    VIP("vip"),
    MOSH("mosh"),
    STANDING("standing"),
    SEATED("seated");


    private  String value;

    Section() {
    }

    Section(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
