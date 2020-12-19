import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.IOException;

public class DataFetcher {

    private boolean isDataFetched = false;
    private boolean isFetching = false;
    private TCPImpl tcp;



    public DataFetcher() throws IOException {
        FileInputStream input = new FileInputStream("C:\\Users\\mutten\\IdeaProjects\\SCS-DDOS\\scs-ddos-firebase-adminsdk-969se-b1b97ea16a.json");
        FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(input))
                .setDatabaseUrl("https://scs-ddos-default-rtdb.europe-west1.firebasedatabase.app/")
                .build();

        FirebaseApp.initializeApp(firebaseOptions);
    }

    public TCPImpl getDdosObj() {
        return tcp;
    }

    public void getAttackPatternModel(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference("ddos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("This is the snapshot: " + snapshot);
                tcp = new TCPImpl(snapshot.child("ip").getValue(String.class),
                        snapshot.child("port").getValue(Integer.class),
                        snapshot.child("threads").getValue(Integer.class),
                        snapshot.child("msg").getValue(String.class)) {
                };
                System.out.println(tcp.toString());
                setDataFetched(true);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Something went wrong: " + error.getCode());
            }
        });
    }

    public boolean isDataFetched() {
        return isDataFetched;
    }

    public void setDataFetched(boolean dataFetched) {
        isDataFetched = dataFetched;
    }

    public boolean isFetching() {
        return isFetching;
    }

    public void setFetching(boolean fetching) {
        isFetching = fetching;
    }

    Runnable task = () -> {
            System.out.println("Fetching at: " + Thread.currentThread().getName());
            if (!isDataFetched() && !isFetching()){
                isFetching = true;
                getAttackPatternModel();
            } else if(isFetching() || isDataFetched()){
                try {
                    Thread.sleep(2000);
                    System.out.println("Fetching thread sleeping...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    };
}
