package com.dd.scs_ddos_project_hive.nmap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import com.dd.scs_ddos_project_hive.R;
import com.dd.scs_ddos_project_hive.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class NmapBinaryInstaller {

    Context context;
    private static final String TAG = "NmapBinaryInstaller";

    public NmapBinaryInstaller (Context context) {
        this.context = context;
    }

    public boolean installResources() {
        InputStream inputStream;
        File outFile;

        @SuppressLint("WrongConstant")
        File appBinHome = context.getDir("bin", Context.MODE_MULTI_PROCESS);

        Resources resources = context.getResources();

        try {
            // Delete the file before we write anything there
            Utils.execCommand("rm -rf ./", appBinHome);

            inputStream = resources.openRawResource(R.raw.nmap);
            outFile = new File(appBinHome, "nmap");
            moveBinaryRawResourceToFile(inputStream, outFile);

            inputStream = resources.openRawResource(R.raw.nmap_os_db);
            outFile = new File(appBinHome, "nmap-os-db");
            moveBinaryRawResourceToFile(inputStream, outFile);

            inputStream = resources.openRawResource(R.raw.nmap_payloads);
            outFile = new File(appBinHome, "nmap-payloads");
            moveBinaryRawResourceToFile(inputStream, outFile);

            inputStream = resources.openRawResource(R.raw.nmap_protocols);
            outFile = new File(appBinHome, "nmap-protocols");
            moveBinaryRawResourceToFile(inputStream, outFile);

            inputStream = resources.openRawResource(R.raw.nmap_rpc);
            outFile = new File(appBinHome, "nmap-rpc");
            moveBinaryRawResourceToFile(inputStream, outFile);

            inputStream = resources.openRawResource(R.raw.nmap_service_probes);
            outFile = new File(appBinHome, "nmap-service-probes");
            moveBinaryRawResourceToFile(inputStream, outFile);

            inputStream = resources.openRawResource(R.raw.nmap_services);
            outFile = new File(appBinHome, "nmap-services");
            moveBinaryRawResourceToFile(inputStream, outFile);

            String []binaries = {"nmap", "nmap-os-db", "nmap-payloads", "nmap-protocols", "nmap-rpc", "nmap-service-probes", "nmap-services"};
            String output;

            // Changing all the permissions of the files in the app_bin folder
            for(int i=0; i<binaries.length ; i++) {
                output = Utils.execCommand("chmod 6755 ./" + binaries[i], appBinHome.getAbsoluteFile());
                Log.d(TAG, "chmod output: " + Utils.execCommand("ls -la ./"+binaries[i], appBinHome.getAbsoluteFile()));
            }
        }
        catch (IOException e) {
            Toast.makeText(context, "IOException!", Toast.LENGTH_LONG).show();
            Log.d(TAG, e.getMessage());
        }
        catch (InterruptedException e) {
            Toast.makeText(context, "Command execution Interrupted!", Toast.LENGTH_LONG).show();
            Log.d(TAG, "Interrupted Exception: " + e.getMessage());
        }

        return true;
    }

    /*
    Write from an input stream to an output file
    */
    private void moveBinaryRawResourceToFile(InputStream inputStream, File outFile) throws IOException {
        byte[] buf = new byte[1024];
        int bytecount;
        OutputStream outputStream = new FileOutputStream(outFile.getAbsolutePath());
        while((bytecount = inputStream.read(buf)) > 0) {
            outputStream.write(buf, 0, bytecount);
        }
        inputStream.close();
        outputStream.close();
    }
}
