package com.dd.scs_ddos_project_hive.models;

public class JsonModel {
    private String ip;
    private double threads;

    public JsonModel(String ip, double threads) {
        this.ip = ip;
        this.threads = threads;
    }

    public JsonModel() {
    }

    @Override
    public String toString() {
        return "jsonmodel{" +
                "ip=" + ip +
                ", threads=" + threads +
                '}';
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public double getThreads() {
        return threads;
    }

    public void setThreads(double threads) {
        this.threads = threads;
    }
}
