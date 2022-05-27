package kg.inai.loginregister;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private String email, password;
    private String URL = "http://10.0.2.2/Login/login.php"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = password = "";
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail)

    }

    public void login(View view) {
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        if(!email.equals("") && !password.equals("")){
            StringRequest stringRequest = new StringRequest(Request > Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        response intent = new Intent(MainActivity.this, Succes.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failure")) {
                        Toast.makeText(MainActivity.this, "Invalid login ID/Password", Toast.LENGTH_SHORT).show();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", email)
                    data.put("password", password)
                    return super data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else {
            Toast.makeText(this, "Field can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }
}