package com.ngoding.aran.tapilkansms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PenerimaSmsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvSmsDari;
    private TextView tvSmsPesan;
    private Button btnTutup;
    public static final String EXTRA_SMS_NO = "extra_sms_no";
    public static final String EXTRA_SMS_PESAN = "extra_sms_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penerima_sms);
        tvSmsDari = (TextView)findViewById(R.id.tv_nomor);
        tvSmsPesan = (TextView)findViewById(R.id.tv_pesan);
        btnTutup = (Button)findViewById(R.id.btn_tutup);
        btnTutup.setOnClickListener(this);

        String senderNo = getIntent().getStringExtra(EXTRA_SMS_NO);
        tvSmsDari.setText("from : "+senderNo);

        String senderMessage = getIntent().getStringExtra(EXTRA_SMS_PESAN);
        tvSmsPesan.setText(senderMessage);
    }

    @Override
    public void onClick(View v) {

    }
}
