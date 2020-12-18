package com.dd.scs_ddos_project_hive.ui.ddos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dd.scs_ddos_project_hive.R;
import com.dd.scs_ddos_project_hive.controllers.AttackController;

public class DDOSFragment extends Fragment {

    private DDOSViewModel ddosViewModel;
    private static final String TAG = "DDOSFragment";
    private AttackController attackController;
    private TextView textView;
    private Button btn;
    private EditText et_ddos, et_tc, et_port, et_msg;

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        ddosViewModel = new ViewModelProvider(this).get(DDOSViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ddos, container, false);

        // Controller for firebase
        attackController = new AttackController();

        // Widgets
        textView = root.findViewById(R.id.text_gallery);
        et_ddos = root.findViewById(R.id.et_ddos);
        et_tc = root.findViewById(R.id.et_tc);
        et_msg = root.findViewById(R.id.et_msg);
        et_port = root.findViewById(R.id.et_port);
        btn = root.findViewById(R.id.button);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            String ip = bundle.getString("ip");
            et_ddos.setText(ip);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = et_ddos.getText().toString();
                String msg = et_msg.getText().toString();
                int pt = Integer.parseInt(et_port.getText().toString());
                int tc = Integer.parseInt(et_tc.getText().toString());

                attackController.writeJson(ip, tc, msg, pt);
                //attackController.sendData(et_ddos.getText().toString());
                ddosViewModel.setmText("new value: " + ip + "  :  " +  tc);
            }
        });

        ddosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        /*if (attackController.receiveData() != null){
            textView.setText(attackController.receiveData().toString());
        }else{
            textView.setText("No Data from firebase");
        }*/
    }
}