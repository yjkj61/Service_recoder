package com.yjkj.service_recoder.java.bean;

import android.graphics.drawable.Drawable;

public class HomeGridBean {
    private String name;
    private int drawable;

    public HomeGridBean(String name, int drawable) {
        this.name = name;
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
