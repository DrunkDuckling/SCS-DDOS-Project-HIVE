package com.dd.scs_ddos_project_hive.ui.scanip;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dd.scs_ddos_project_hive.helpers.NetworkSniffTask;
import com.dd.scs_ddos_project_hive.models.IPModel;

import java.util.Map;

public class ScanIPViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private MutableLiveData<IPModel> iplist;

    private NetworkSniffTask snifsnif;


    public void ScanIP(Context context){
        // Sniffer dog
        snifsnif = new NetworkSniffTask(context);
        snifsnif.execute();

    }


    public ScanIPViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}