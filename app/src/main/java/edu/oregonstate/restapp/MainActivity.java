package edu.oregonstate.restapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.Button;

import edu.oregonstate.restapp.adapters.SequenceAdapter;
import edu.oregonstate.restapp.clients.SequenceRestClient;
import edu.oregonstate.restapp.models.Sequence;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

/**
 * class which stores methods for
 */
public class MainActivity extends AppCompatActivity {

    /* Activity Objects */
    private ListView sequenceList;
    private Button addActivity;

    /**
     * Method that is called upon opening RESTAPP
     * @param savedInstanceState stored instance of view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* If data stored in savedInstanceState, restores previously saved data */
        super.onCreate(savedInstanceState);

        /* Creates layout on page */
        setContentView(R.layout.activity_main);

        /* retrieves sequences from REST API */
        getSequences();

        /* adds listener for button to add sequence */
        addListenerOnButton();
    }

    /**
     * function to query REST API and get sequences from database
     * add to ListView sequenceList
     */
    private void getSequences() {

        /* Headers  */
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept", "application/json"));

        /* Create parameters for request */
        RequestParams params = new RequestParams();
        params.put("action", "list");

        /* HTTP GET request */
        SequenceRestClient.get(MainActivity.this, "requests", headers.toArray(new Header[headers.size()]),
                params, new JsonHttpResponseHandler() {

                    /* Overrides default onSuccess call */
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        /* Creates array list to store sequences */
                        ArrayList<Sequence> sequenceArray = new ArrayList<>();

                        /* Creates adapter to display sequences in the MainActivity */
                        SequenceAdapter sequenceAdapter = new SequenceAdapter(MainActivity.this, sequenceArray);

                        /* Takes the array returned by HTTP GET request
                         * Iterates over array and adds sequence objects
                         * To adapter OR prints error in console */
                        try {
                            String data = response.getString("list");
                            JSONArray arr = new JSONArray(data);
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject obj = arr.getJSONObject(i);
                                sequenceAdapter.add(new Sequence(obj));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        /* Adds items from the HTTP GET JSON object to the view */
                        sequenceList = (ListView) findViewById(R.id.list_sequence);

                        /* Sets adapter to display sequences */
                        sequenceList.setAdapter(sequenceAdapter);
                    }
                });
    }

    /**
     * function to listen for button "click"
     * goes to Add Activity
     */
    public void addListenerOnButton() {

        /* Adds XML button id to Button object */
        addActivity = (Button) findViewById(R.id.button_add_activity);

        /* Adds event listener to Button object */
        addActivity.setOnClickListener(new View.OnClickListener() {

            /* Overrides default onClick call */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }
}