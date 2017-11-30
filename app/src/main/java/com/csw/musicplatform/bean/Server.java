package com.csw.musicplatform.bean;

/**
 * Created by caisw on 2017/11/30.
 */

public class Server {

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
