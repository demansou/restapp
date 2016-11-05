package edu.oregonstate.restapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import edu.oregonstate.restapp.adapters.SequenceAdapter;
import edu.oregonstate.restapp.clients.SequenceRestClient;
import edu.oregonstate.restapp.models.Sequence;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class MainActivity extends AppCompatActivity {

    private ListView sequenceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getNotes();
    }

    private void getNotes() {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));

        SequenceRestClient.get(MainActivity.this, "requests", headers.toArray(new Header[headers.size()]),
                null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        ArrayList<Sequence> sequenceArray = new ArrayList<Sequence>();
                        SequenceAdapter sequenceAdapter = new SequenceAdapter(MainActivity.this, sequenceArray);

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                sequenceAdapter.add(new Sequence(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        sequenceList = (ListView) findViewById(R.id.list_sequence);
                        sequenceList.setAdapter(sequenceAdapter);
                    }
                });
    }
}