package com.example.projectedesa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button btn_login;
    private ProgressBar loading;
//    192.168.100.14
    private static String URL_LOGIN = "http://192.168.100.14/GitHub/ProjectWebService/api/c_login";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        loading = findViewById(R.id.loading);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mUser = username.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if (!mUser.isEmpty() || !mPass.isEmpty()) {
                    Login(mUser, mPass);
                } else {
                    username.setError("Tolong masukkan username");
                    password.setError("Tolong masukkan password");
                }
            }
        });

    }

    private void Login(final String username, final String password) {
        loading.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);

                            String nama = object.getString("nama").trim();
                            String username = object.getString("username").trim();

                            sessionManager.createSession(nama, username);

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class );
                            intent.putExtra("nama", nama);
                            intent.putExtra("username", username);
                            startActivity(intent);

                            loading.setVisibility(View.GONE);

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    loading.setVisibility(View.GONE);
                    btn_login.setVisibility(View.VISIBLE);
                    Toast.makeText(LoginActivity.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.setVisibility(View.GONE);
                btn_login.setVisibility(View.VISIBLE);
                Toast.makeText(LoginActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}