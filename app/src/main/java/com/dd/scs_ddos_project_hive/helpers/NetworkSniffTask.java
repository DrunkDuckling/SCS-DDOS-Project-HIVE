package com.dd.scs_ddos_project_hive.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.text.format.Formatter;
import android.util.Log;

import com.dd.scs_ddos_project_hive.models.IPModel;

import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

// https://stackoverflow.com/questions/36277912/how-to-scan-ip-in-android
// https://stackoverflow.com/questions/39335835/how-to-get-ip-address-and-names-of-all-devices-in-local-network-on-android
public class NetworkSniffTask extends AsyncTask<Void, Void, List<IPModel>> {

    private static final String TAG = "NetworkSniffTask";
    private WeakReference<Context> mContextRef;


    public NetworkSniffTask(Context context) {
        mContextRef = new WeakReference<Context>(context);
    }

    @Override
    protected List<IPModel> doInBackground(Void... voids) {
        List<IPModel> iplist = new ArrayList<>();
        Log.d(TAG, "Let's sniff the network");
        String hostName = "";
        try {
            Context context = mContextRef.get();

            if (context != null) {

                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                WifiInfo connectionInfo = wm.getConnectionInfo();
                int ipAddress = connectionInfo.getIpAddress();
                String macAddress = connectionInfo.getMacAddress();
                String BSSID = connectionInfo.getBSSID();
                String ipString = Formatter.formatIpAddress(ipAddress);


                Log.d(TAG, "activeNetwork: " + String.valueOf(activeNetwork));
                Log.d(TAG, "ipString: " + String.valueOf(ipString));
                Log.d(TAG, "macAddress: " + macAddress);
                Log.d(TAG, "BSSID: " + BSSID);

                String prefix = ipString.substring(0, ipString.lastIndexOf(".") + 1);
                Log.d(TAG, "prefix: " + prefix);

                for (int i = 0; i < 255; i++) {
                    String testIp = prefix + String.valueOf(i);

                    InetAddress address = InetAddress.getByName(testIp);
                    boolean reachable = address.isReachable(1);
                    hostName = address.getCanonicalHostName();

                    String hostAddr = address.getHostAddress();

                    if (reachable) {
                        iplist.add(new IPModel(hostName));
                        Log.i(TAG, "Testing values: Addr: " + address + "Host: " + hostName + " HostAdr: " + hostAddr);
                        Log.i(TAG, "Host: " + String.valueOf(hostName) + "(" + String.valueOf(testIp) + ") is reachable!");

                    }
                }

            }
        } catch (Throwable t) {
            Log.e(TAG, "Well that's not good.", t);
        }
        return iplist;
    }

    @Override
    protected void onPostExecute(List<IPModel> s) {
        super.onPostExecute(s);
        CONSTANT.ASYNCTASKRUNNING = true;
    }

// TODO Testing stage, maybe do it. Hwo knows
    /*String macAdress = "5caafd1b0019";
    String dataUrl = "http://api.macvendors.com/" + macAdress;
    HttpURLConnection connection = null;
    try {
        URL url = new URL(dataUrl);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.flush();
        wr.close();
        InputStream is = connection.getInputStream();

        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuffer response = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);response.append('\r');
        }
        rd.close();
        String responseStr = response.toString();
        Log.d("Server response", responseStr);
    } catch (Exception e) {
        e.printStackTrace();
    }finally {
        if (connection != null) {
            connection.disconnect();
        }
    }*/

}


