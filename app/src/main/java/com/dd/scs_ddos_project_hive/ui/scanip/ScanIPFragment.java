package com.dd.scs_ddos_project_hive.ui.scanip;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dd.scs_ddos_project_hive.R;
import com.dd.scs_ddos_project_hive.controllers.RecyclerViewAdapter;
import com.dd.scs_ddos_project_hive.helpers.CONSTANT;
import com.dd.scs_ddos_project_hive.helpers.NetworkSniffTask;
import com.dd.scs_ddos_project_hive.models.IPModel;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static java.lang.Thread.sleep;

public class ScanIPFragment extends Fragment {

    private static final String TAG = "ScanIPFragment";
    private ScanIPViewModel scanIPViewModel;
    private NetworkSniffTask snifsnif;
    private boolean whenInVeiw;

    RecyclerViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        scanIPViewModel = new ViewModelProvider(this).get(ScanIPViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ip_snif, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.fg_ip);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerViewAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Loading UI
        ProgressDialog progress = new ProgressDialog(getContext());
        progress.setTitle("Scanning");
        progress.setMessage("Seaching for IPs...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        scanIPViewModel.getModelList().observe(getViewLifecycleOwner(), new Observer<List<IPModel>>() {
            @Override
            public void onChanged(List<IPModel> ipModels) {
                // To dismiss the dialog
                if (ipModels.size() > 0) {
                    // To dismiss the dialog
                    progress.dismiss();
                }
                adapter = new RecyclerViewAdapter(ipModels);
                recyclerView.setAdapter(adapter);
            }
        });

        whenInVeiw = true;
        new Thread(() -> {
            while(whenInVeiw){
                if (CONSTANT.ASYNCTASKRUNNING){
                    CONSTANT.ASYNCTASKRUNNING = false;
                    scanIPViewModel.ScanIP(getContext(), getActivity());
                }
            }
        }).start();

        return root;
    }



    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        whenInVeiw = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        whenInVeiw = false;
    }
}