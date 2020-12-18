package com.dd.scs_ddos_project_hive.controllers;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dd.scs_ddos_project_hive.models.JsonModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class AttackController extends ViewModel {

    private final DatabaseReference myRef;
    private MutableLiveData<String> mData;

    public AttackController(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ddos");
    }

    public void writeJson(String ip, double tc, String msg, int port){
        JsonModel model = new JsonModel(ip, tc, msg, port);
        myRef.setValue(model);
    }

    public Task<Void> sendData(String input){
        return myRef.setValue(input);
    }

    public LiveData<String> getData() {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mData.setValue(snapshot.getValue(String.class));
                Log.d(TAG, "Value is: " + mData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException() );

            }
        });

        return mData;
    }

    public Task<Void> receiveData(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException() );

            }
        });
        return null;
    }
}
