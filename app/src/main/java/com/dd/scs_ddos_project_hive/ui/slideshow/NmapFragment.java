package com.dd.scs_ddos_project_hive.ui.slideshow;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dd.scs_ddos_project_hive.R;
import com.dd.scs_ddos_project_hive.helpers.ClipBoard;
import com.dd.scs_ddos_project_hive.nmap.NmapBinaryInstaller;
import com.dd.scs_ddos_project_hive.nmap.Utils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import static android.content.Context.MODE_MULTI_PROCESS;

public class NmapFragment extends Fragment {

    private NmapViewModel nmapViewModel;

    ClipBoard clipBoard;

    String DEBUG_TAG = "myTag";
    String DEFAULT_SHARED_PREFERENCES = "mySharedPrefs";
    String firstStartPref = "firstStart";
    public static File appBinHome;
    String NMAP_COMMAND = "./nmap ";
    public static TextView scanResult = null;

    private Context mContext;

    @SuppressLint("WrongConstant")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        nmapViewModel = new ViewModelProvider(this).get(NmapViewModel.class);
        View root = inflater.inflate(R.layout.fragment_nmap, container, false);

        clipBoard = new ClipBoard(mContext);

        boolean firstInstall = true;
        appBinHome = mContext.getDir("bin", MODE_MULTI_PROCESS);

        SharedPreferences mySharedPreferences = mContext.getSharedPreferences(DEFAULT_SHARED_PREFERENCES, MODE_MULTI_PROCESS);
        firstInstall = mySharedPreferences.getBoolean(firstStartPref, true);
        if(firstInstall) {
            NmapBinaryInstaller installer = new NmapBinaryInstaller(getContext());
            installer.installResources();
            Log.d(DEBUG_TAG, "Installing binaries");
            // TODO: Write some test code to see if the binaries are placed correctly and have the right permissions!
            mySharedPreferences.edit().putBoolean(firstStartPref, false).commit();
        }

        Button scan = (Button)root.findViewById(R.id.scan_BT);
        Button paste_btn = root.findViewById(R.id.paste_BT);
        final EditText flags = (EditText)root.findViewById(R.id.flags_ET);
        scanResult = (TextView)root.findViewById(R.id.scan_output_TV);

        scanResult.setMovementMethod(new ScrollingMovementMethod());

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f = flags.getText().toString();
                new AsyncCommandExecutor().execute( NMAP_COMMAND + f);
            }
        });

        paste_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p = clipBoard.pasteStringData();
                flags.append(p);
            }
        });
        return root;
    }

    public class AsyncCommandExecutor extends AsyncTask<String, Void, Void> {

        public String returnOutput;
        private ProgressDialog progressDialog = new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            this.progressDialog.setTitle("NMAP");
            this.progressDialog.setMessage("Scanning...");
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
            return;
        }
        @Override
        protected Void doInBackground(String... params) {
            try {
                this.returnOutput = Utils.execCommand(params[0], NmapFragment.appBinHome.getAbsoluteFile());
            } catch (IOException e) {
                this.returnOutput = "IOException while trying to scan!";
                Log.d(DEBUG_TAG, e.getMessage());
            } catch (InterruptedException e) {
                this.returnOutput = "Nmap Scan Interrupted!";
                Log.d(DEBUG_TAG, e.getMessage());
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            NmapFragment.scanResult.setText(returnOutput);
            if(this.progressDialog.isShowing())
                this.progressDialog.dismiss();
        }
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }
}