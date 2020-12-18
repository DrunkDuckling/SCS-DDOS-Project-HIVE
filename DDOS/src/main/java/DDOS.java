import java.io.Serializable;

public class DDOS implements Serializable {
    private String ip;
    private String msg;
    private int port = 80;
    private int threads;


    public DDOS(String ip, int port, int threads, String msg) {
        this.ip = ip;
        this.msg = msg;
        this.port = port;
        this.threads = threads;
    }



    public String getVictimAddress() {
        return ip;
    }

    public void setVictimAddress(String ip) {
        this.ip = ip;
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

    public int getThreadCount() {
        return threads;
    }

    public void setThreadCount(int threads) {
        this.threads = threads;
    }

    @Override
    public String toString() {
        return "DDOS{" +
                "ip='" + ip + '\'' +
                ", msg='" + msg + '\'' +
                ", port=" + port +
                ", threads=" + threads +
                '}';
    }
}
