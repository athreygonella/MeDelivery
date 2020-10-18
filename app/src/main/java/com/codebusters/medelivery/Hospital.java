package com.codebusters.medelivery;

public class Hospital {
    private String name;
    private String address;
    public Hospital(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "Name: " + name + "; Address: " + address;
    }
}
