package com.example.projectedesa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class PengaturanFragment extends Fragment implements View.OnClickListener {

    private String JSON_STRING;

    EditText oldpass;
    EditText newpass;
    Button btn;

    String mName;
    String mUsername;

    SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pengaturan, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        mName = user.get(sessionManager.NAMA);
        mUsername = user.get(sessionManager.USERNAME);

        oldpass = (EditText) getView().findViewById(R.id.oldpassword);
        newpass = (EditText) getView().findViewById(R.id.newpassword);
        btn = (Button) getView().findViewById(R.id.btnubah);

        btn.setOnClickListener(this);

    }

    private void updatePassword(){
        final String oldp = oldpass.getText().toString().trim();
        final String newp = newpass.getText().toString().trim();

        class UpdatePassword extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                s = "UPDATE SUKSES";
                loading.dismiss();
                Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_USERNAME,mUsername);
                hashMap.put(konfigurasi.KEY_PASSWORD_NEW,newp);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_PASSWORD,hashMap);

                return s;
            }
        }

        UpdatePassword ue = new UpdatePassword();
        ue.execute();
    }

    @Override
    public void onClick(View view) {
        updatePassword();
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
    }
}
