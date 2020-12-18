package com.dd.scs_ddos_project_hive.controllers;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.dd.scs_ddos_project_hive.R;
import com.dd.scs_ddos_project_hive.models.IPModel;
import com.dd.scs_ddos_project_hive.ui.ddos.DDOSFragment;

import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    Context context;

    List<IPModel> mIPlist;

    public RecyclerViewAdapter(List<IPModel> mIPlist) {
        this.mIPlist = mIPlist;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_ip_list, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ip_text = mIPlist.get(position).getIp();
        String mac = mIPlist.get(position).getMac();
        holder.txt_ip_mac.setText(mac);
        holder.txt_ip_addr.setText(ip_text);

        holder.txt_ip_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + holder.txt_ip_addr.getText());
                Toast.makeText(context, holder.txt_ip_addr.getText(), Toast.LENGTH_SHORT).show();

                DDOSFragment fragment = new DDOSFragment();
                Bundle bundle = new Bundle();
                bundle.putString("ip", holder.txt_ip_addr.getText().toString());
                fragment.setArguments(bundle);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mIPlist.size();
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
