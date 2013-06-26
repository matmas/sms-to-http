package net.matmas.smstohttp.receivers;

import net.matmas.smstohttp.Logic;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    SmsMessage[] messages = new SmsMessage[pdus.length];
                    for (int i = 0; i < messages.length; i++) {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        
                        String from = messages[i].getOriginatingAddress();
                        String body = messages[i].getMessageBody();
                        
                        Logic.onSMSReceive(from, body);
                    }
                } catch(Exception e) {
                	Log.d("Exception caught", e.getMessage());
//                	Toast.makeText(App.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
