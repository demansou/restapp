package edu.oregonstate.restapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.Button;
import android.util.Log;

import edu.oregonstate.restapp.adapters.ClusterAdapter;
import edu.oregonstate.restapp.clients.SequenceRestClient;
import edu.oregonstate.restapp.models.Cluster;

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
    private ListView clusterList;
    private Button addCluster;
    private Button loginButton;

    /**
     * Method that is called upon opening RESTAPP
     * @param savedInstanceState stored instance of view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* If data stored in savedInstanceState, restores previously saved data */
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        /* retrieves sequences from REST API */
        getClusters();

        /* adds listener for button to add sequence */
        addListenerOnButton();

        addListenerOnLoginButton();

        addListenerOnLogout();
    }

    /**
     * function to query REST API and get clusters from database
     * add to ListView clusterList
     */
    private void getClusters() {

        /* Headers  */
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept", "application/json"));

        /* Set User Auth Token */
        String user_auth_token = ((MyApplication) this.getApplication()).getUserAuthToken();

        /* Create parameters for request */
        RequestParams params = new RequestParams();
        params.add("user_auth_token", user_auth_token);

        //Log.d("debug", user_auth_token);

        /* HTTP GET request */
        SequenceRestClient.get(MainActivity.this, "requests", headers.toArray(new Header[headers.size()]),
                params, new JsonHttpResponseHandler() {

                    /* Overrides default onSuccess call */
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                        /* Creates array list to store sequences */
                        ArrayList<Cluster> clusterArray = new ArrayList<>();

                        /* Creates adapter to display sequences in the MainActivity */
                        ClusterAdapter clusterAdapter = new ClusterAdapter(MainActivity.this, clusterArray);

                        /* Takes the array returned by HTTP GET request
                         * Iterates over array and adds sequence objects
                         * To adapter OR prints error in console */
                        try {
                            Log.d("debug", response.getString("data"));
                            String data = response.getString("data");
                            JSONObject newObj = new JSONObject(data);
                            String cluster_list = newObj.getString("cluster_list");
                            JSONArray arr = new JSONArray(cluster_list);
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject obj = arr.getJSONObject(i);
                                clusterAdapter.add(new Cluster(obj));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        /* Adds items from the HTTP GET JSON object to the view */
                        clusterList = (ListView) findViewById(R.id.list_cluster);

                        /* Sets adapter to display sequences */
                        clusterList.setAdapter(clusterAdapter);
                    }
                });
    }

    /**
     * function to listen for button "click"
     * goes to Add Activity
     */
    public void addListenerOnButton() {

        /* Adds XML button id to Button object */
        addCluster = (Button) findViewById(R.id.button_add_cluster);

        /* Adds event listener to Button object */
        addCluster.setOnClickListener(new View.OnClickListener() {

            /* Overrides default onClick call */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addListenerOnLoginButton() {
        loginButton = (Button) findViewById(R.id.button_login);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }

    public void addListenerOnLogout() {
        loginButton = (Button) findViewById(R.id.button_logout);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((MyApplication) getApplication()).setUserAuthToken("");
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}