package edu.oregonstate.restapp;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.util.Log;

import edu.oregonstate.restapp.clients.SequenceRestClient;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity {
    /* Login Objects */
    private Button submitButton;
    private EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        submitForm();
    }

    public void submitForm() {
        submitButton = (Button) findViewById(R.id.button_login_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                userName = (EditText) findViewById(R.id.edit_user_name);

                String UserName = userName.getText().toString();

                RequestParams params = new RequestParams();
                params.add("action", "user");
                params.add("user_name", UserName);

                SequenceRestClient.post("requests", params, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            String data = response.getString("data");
                            JSONObject obj = new JSONObject(data);
                            String user_auth_token = obj.getString("user_auth_token");
                            ((MyApplication) getApplication()).setUserAuthToken(user_auth_token);
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                });
            }
        });
    }

}
