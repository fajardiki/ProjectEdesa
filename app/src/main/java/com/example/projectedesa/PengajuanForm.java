package com.example.projectedesa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class PengajuanForm extends AppCompatActivity {

    Toolbar ToolBarAtas2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan_form);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri

        ToolBarAtas2 = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(ToolBarAtas2);
        ToolBarAtas2.setLogoDescription(getResources().getString(R.string.app_name));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
