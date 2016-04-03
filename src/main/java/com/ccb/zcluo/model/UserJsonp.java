package com.ccb.zcluo.model;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zcluo on 2016/4/3.
 */
public class UserJsonp {
    public UserJsonp(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static interface OnlyIdView {}
    public static interface OnlyNameView {}
    public static interface AllView extends OnlyIdView, OnlyNameView {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(OnlyIdView.class)
    private Long id;

    @JsonView(OnlyNameView.class)
    private String name;



}
