package com.dd.scs_ddos_project_hive.models;

public class JsonModel {
    private String ip;
    private int threads;
    private String msg;
    private int port;


    public JsonModel(String ip, int threads, String msg, int port) {
        this.ip = ip;
        this.threads = threads;
        this.msg = msg;
        this.port = port;
    }

    public JsonModel() {
    }

    @Override
    public String toString() {
        return "JsonModel{" +
                "ip='" + ip + '\'' +
                ", threads=" + threads +
                ", msg='" + msg + '\'' +
                ", port=" + port +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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

    public void setThreads(int threads) {
        this.threads = threads;
    }
}
