package com.dd.scs_ddos_project_hive.ui.scanip;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dd.scs_ddos_project_hive.R;
import com.dd.scs_ddos_project_hive.helpers.CONSTANT;
import com.dd.scs_ddos_project_hive.helpers.NetworkSniffTask;

import java.lang.ref.WeakReference;

import static java.lang.Thread.sleep;

public class ScanIPFragment extends Fragment {

    private static final String TAG = "ScanIPFragment";
    private ScanIPViewModel scanIPViewModel;
    private NetworkSniffTask snifsnif;
    private boolean whenInVeiw;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        scanIPViewModel = new ViewModelProvider(this).get(ScanIPViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);
        scanIPViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        whenInVeiw = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(whenInVeiw){
                    if (CONSTANT.ASYNCTASKRUNNING){
                        CONSTANT.ASYNCTASKRUNNING = false;
                        scanIPViewModel.ScanIP(getContext());
                        Log.d(TAG, "run: ");
                    }
                }
            }
        }).start();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        whenInVeiw = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        whenInVeiw = false;
    }
}