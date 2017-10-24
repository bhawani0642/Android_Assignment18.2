package com.acadgild.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by Pri on 10/24/2017.
 */

public class SmsBroadcastReceiver extends BroadcastReceiver {
    //declaration
    public static final String SMS_BUNDLE = "pdus";

    //Method onReceive()
    public void onReceive(Context context, Intent intent) {
        //creating bundle and getting info
        Bundle intentExtras = intent.getExtras();
        //Applying Condition
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            //initializating Sms
            for (int i = 0; i < sms.length; ++i) {
                //Creating object of smsMessage
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);
                //Taking String
                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();

                smsMessageStr += "senderNum:  " + address + ",\n";
                smsMessageStr += smsBody + "\n";
            }
            Toast.makeText(context, smsMessageStr, Toast.LENGTH_SHORT).show();//showing toast

            //this will update the UI with message
            MainActivity inst = MainActivity.instance();
            inst.updateList(smsMessageStr);
        }
    }
}