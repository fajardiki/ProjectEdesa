package com.example.projectedesa;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    private ListView listView;
    TextView User;

    private String JSON_STRING;

    SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();

        listView = (ListView) getView().findViewById(R.id.listview);
        User = (TextView) getView().findViewById(R.id.user);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAMA);
        String mUsername = user.get(sessionManager.USERNAME);


        User.setText("Selamat datang "+mName);

        getJSON();
    }

    private void showPengumuman(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String judul = jo.getString(konfigurasi.TAG_JUDUL_PENGUMUMAN);
                String waktu = jo.getString(konfigurasi.TAG_WAKTU_PENGUMUMAN);
                String isi = jo.getString(konfigurasi.TAG_ISI_PENGUMUMAN);

                HashMap<String,String> pengumuman = new HashMap<>();
                pengumuman.put(konfigurasi.TAG_JUDUL_PENGUMUMAN,judul);
                pengumuman.put(konfigurasi.TAG_WAKTU_PENGUMUMAN,waktu);
                pengumuman.put(konfigurasi.TAG_ISI_PENGUMUMAN,isi);
                list.add(pengumuman);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                getActivity(), list, R.layout.list_item,
                new String[]{konfigurasi.TAG_JUDUL_PENGUMUMAN,konfigurasi.TAG_WAKTU_PENGUMUMAN,konfigurasi.TAG_ISI_PENGUMUMAN},
                new int[]{R.id.judul, R.id.waktu, R.id.isi});

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
                String s = rh.sendGetRequest(konfigurasi.URL_PENGUMUMAN_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
}
