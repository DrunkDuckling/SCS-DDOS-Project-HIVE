package com.dd.scs_ddos_project_hive.ui.scanip;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dd.scs_ddos_project_hive.helpers.NetworkSniffTask;
import com.dd.scs_ddos_project_hive.models.IPModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ScanIPViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private MutableLiveData<List<IPModel>> iplist;

    private NetworkSniffTask snifsnif;


    public void ScanIP(Context context, Activity activity){
        // Sniffer dog
        snifsnif = new NetworkSniffTask(context);
        try {
            List<IPModel> result = snifsnif.execute().get();

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    iplist.setValue(result);
                }
            });

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public ScanIPViewModel() {
        iplist = new MutableLiveData<>();
        iplist.setValue(new ArrayList<>());
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<IPModel>> getModelList() {
        return iplist;
    }
}