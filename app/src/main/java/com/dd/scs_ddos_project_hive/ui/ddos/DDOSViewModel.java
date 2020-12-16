package com.dd.scs_ddos_project_hive.ui.ddos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dd.scs_ddos_project_hive.controllers.AttackController;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DDOSViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mData;
    private AttackController attackController;

    public DDOSViewModel() {
        mText = new MutableLiveData<>();
        mData = new MutableLiveData<>();
        mText.setValue("This is DDOS fragment");
    }

    public void setmText(String mText) {
        this.mText.setValue(mText);
    }

    public LiveData<String> getData() {

        return mData;
    }

    public LiveData<String> getText() {

        return mText;
    }
}