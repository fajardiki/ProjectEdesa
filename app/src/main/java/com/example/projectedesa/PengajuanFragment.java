package com.example.projectedesa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.HashMap;

public class PengajuanFragment extends Fragment implements View.OnClickListener {

    private ListView listView;

    private String JSON_STRING;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pengajuan, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) getView().findViewById(R.id.listpengajuan);
        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        getJSON();

        fab.setOnClickListener(this);
    }

    private void showPengumuman(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String nama_surat = jo.getString(konfigurasi.TAG_NAMASURAT_PENGAJUAN);
                String tanggal = jo.getString(konfigurasi.TAG_TANGGAL_PENGAJUAN);
                String status = jo.getString(konfigurasi.TAG_STATUS_PENGAJUAN);

                HashMap<String,String> pengajuan = new HashMap<>();
                pengajuan.put(konfigurasi.TAG_NAMASURAT_PENGAJUAN, nama_surat);
                pengajuan.put(konfigurasi.TAG_TANGGAL_PENGAJUAN, tanggal);
                pengajuan.put(konfigurasi.TAG_STATUS_PENGAJUAN, status);
                list.add(pengajuan);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                getActivity(), list, R.layout.list_pengajuan,
                new String[]{konfigurasi.TAG_NAMASURAT_PENGAJUAN,konfigurasi.TAG_TANGGAL_PENGAJUAN,konfigurasi.TAG_STATUS_PENGAJUAN},
                new int[]{R.id.nama_surat,R.id.tgl_pengajuan,R.id.status});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showPengumuman();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_PERMOHONAN_ONE);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    @Override
    public void onClick(View view) {
        Intent i = new Intent(getActivity(), PengajuanForm.class);
        startActivity(i);
    }
}
