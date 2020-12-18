import java.util.concurrent.atomic.AtomicInteger;

public class AttackLauncher {

    private TCPAttack tcpAttack;
    private AtomicInteger atomicInteger;



    public AttackLauncher(TCPAttack tcpAttack) {
        this.tcpAttack = tcpAttack;
        atomicInteger = new AtomicInteger();
    }



    Runnable task =  () -> {
        atomicInteger.incrementAndGet();
        tcpAttack.initializeSocket("Test");
        System.out.println("Socket initialized in thread-" + Thread.currentThread().getName());
        while (!Thread.currentThread().isInterrupted() && (!tcpAttack.getSocket().isConnected())) {
            System.out.println("Attack num: " + atomicInteger.get());
            tcpAttack.connect();
            System.out.println("Socket connected in thread-" + Thread.currentThread().getName());
        }
        System.out.println("Connection stopped");
        tcpAttack.stopSocketConnection();
    };









}
