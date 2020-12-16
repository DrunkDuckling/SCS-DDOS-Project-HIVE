package com.dd.scs_ddos_project_hive.controllers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dd.scs_ddos_project_hive.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mip_name = new ArrayList<>();
    private ArrayList<String> mip_mac = new ArrayList<>();
    private ArrayList<String> mip_comp = new ArrayList<>();
    private ArrayList<String> mip_addr = new ArrayList<>();
    private ArrayList<String> mip_img = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> mip_name, ArrayList<String> mip_mac, ArrayList<String> mip_comp, ArrayList<String> mip_addr, ArrayList<String> mip_img) {
        this.mip_name = mip_name;
        this.mip_mac = mip_mac;
        this.mip_comp = mip_comp;
        this.mip_addr = mip_addr;
        this.mip_img = mip_img;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_ip_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_ip_name, txt_ip_mac, txt_ip_comp, txt_ip_addr;
        ImageView imageView_ip;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_ip_name = itemView.findViewById(R.id.txt_ip_name);
            txt_ip_mac = itemView.findViewById(R.id.txt_ip_mac);
            txt_ip_comp = itemView.findViewById(R.id.txt_ip_comp);
            txt_ip_addr = itemView.findViewById(R.id.txt_ip_addr);
            imageView_ip = itemView.findViewById(R.id.imageView_ip);
            parentLayout = itemView.findViewById(R.id.parent);

        }
    }
}
