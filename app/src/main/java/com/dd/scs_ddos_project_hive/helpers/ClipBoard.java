package com.dd.scs_ddos_project_hive.helpers;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

import static android.content.Context.CLIPBOARD_SERVICE;


public class ClipBoard {
    Context context;
    ClipboardManager mClipboardManager;
    ClipData data;

    public ClipBoard(Context mContext) {
        this.context = mContext;
        mClipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
    }

    public void copyStringContent(String txt){
        data = ClipData.newPlainText("text", txt);
        mClipboardManager.setPrimaryClip(data);
        Toast.makeText(context, "Copy: " + txt, Toast.LENGTH_SHORT).show();
    }

    public String pasteStringData(){
        if (mClipboardManager.getPrimaryClip() != null){
            ClipData pData = mClipboardManager.getPrimaryClip();
            ClipData.Item item = pData.getItemAt(0);
            return item.getText().toString();
        }else{
            Toast.makeText(context, "Clipboard empty", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

}
