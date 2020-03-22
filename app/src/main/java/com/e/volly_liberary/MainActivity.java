package com.e.volly_liberary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
TextView textView;
Button btn;
RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        textView=findViewById (R.id.textviewe);
        btn=findViewById (R.id.button);
        requestQueue= Volley.newRequestQueue (this);
        btn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                jsonparsing();
            }


        });

    }
    private void jsonparsing() {
        String url="https://api.myjson.com/bins/7ujfs";
        JsonObjectRequest request=new JsonObjectRequest (Request.Method.GET, url, null,
                new Response.Listener<JSONObject> () {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray =response.getJSONArray ("employees");
                            for (int i=0; i<jsonArray.length ();i++)
                            {
                                JSONObject employeesobject =jsonArray.getJSONObject (i);
                                String fristname =employeesobject .getString ("firstname");
                                int age=employeesobject .getInt ("age");
                                String email=employeesobject .getString ("mail");
                                textView.append (fristname+","+String.valueOf (age)+", "+email+"\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace ();
                        }
                    }
                }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText (MainActivity.this, "Listner is not working", Toast.LENGTH_SHORT).show ();
            }
        });
        requestQueue.add (request);
    }
}
