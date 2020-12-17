package com.dd.scs_ddos_project_hive.models;

public class IPModel {
    private String ip;
    private String mac;
    private String comp;
    private String name;

    public IPModel(String ip, String mac, String comp, String name) {
        this.ip = ip;
        this.mac = mac;
        this.comp = comp;
        this.name = name;
    }

    public IPModel() {
    }

    public IPModel(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
