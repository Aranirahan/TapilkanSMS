package com.ngoding.aran.tapilkansms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import java.io.BufferedReader;

public class SmsReceiver extends BroadcastReceiver {
    final SmsManager sms = SmsManager.getDefault();
    public SmsReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        final Bundle bundle =intent.getExtras();
        try{
            if(bundle != null){
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0;i<pdusObj.length;i++){
                    SmsMessage PesanSaatIni = getPesanMasuk(pdusObj[i], bundle);
                    String nomorTelepon = PesanSaatIni.getDisplayOriginatingAddress();
                    String nomorPengirim = nomorTelepon;
                    String pesan = PesanSaatIni.getDisplayMessageBody();
                    Log.i("SmsReciver", "nomorPengirim : "+ nomorPengirim+"; \nPesan : "+pesan);
                    Intent tampilkanSmsIntent = new Intent(context, SmsReceiver.class);
                    tampilkanSmsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    tampilkanSmsIntent.putExtra(PenerimaSmsActivity.EXTRA_SMS_NO, nomorTelepon);
                    tampilkanSmsIntent.putExtra(PenerimaSmsActivity.EXTRA_SMS_PESAN, pesan);
                    context.startActivity(tampilkanSmsIntent);

                }
            }
        }catch (Exception e){
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
        }

    }
    private SmsMessage getPesanMasuk (Object aObject, Bundle bundle){
        SmsMessage SMSSaatIni;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            String format = bundle.getString("format");
            SMSSaatIni = SmsMessage.createFromPdu((byte[]) aObject, format);
        }else {
            SMSSaatIni = SmsMessage.createFromPdu((byte[]) aObject);
        }
        return SMSSaatIni;
    }

}
