package com.csw.musicplatform.bean;

import java.io.Serializable;

/**
 * Created by caisw on 2017/11/30.
 */

public class Server implements Serializable {

    private String name;
    private String address;

    public Server(String name, String host) {
        this.name = name;
        this.address = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
