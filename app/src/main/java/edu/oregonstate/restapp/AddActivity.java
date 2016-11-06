package edu.oregonstate.restapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import edu.oregonstate.restapp.clients.SequenceRestClient;
import edu.oregonstate.restapp.models.Sequence;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class AddActivity extends AppCompatActivity {

    /* Activity Objects */
    private Button submitButton;
    private EditText textOrganism;
    private EditText textName;
    private EditText textDescription;

    /**
     * Method that is called when accessing AddActivity
     * @param savedInstanceState stored instance of view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* If data stored in savedInstanceState, restores previously saved data */
        super.onCreate(savedInstanceState);

        /* Creates layout on page */
        setContentView(R.layout.activity_add);

        /* retrieves sequences from REST API */
        submitForm();
    }

    /**
     * function to submit form input values
     */
    public void submitForm() {

        /* Adds XML button id to Button object */
        submitButton = (Button) findViewById(R.id.button_sequence_submit);

        /* Adds event listener to Button object */
        submitButton.setOnClickListener(new View.OnClickListener() {

            /* Overrides default onClick call */
            @Override
            public void onClick(View view) {

                /* Adds XML edit text ids to EditText objects */
                textOrganism = (EditText) findViewById(R.id.edit_text_organism);
                textName = (EditText) findViewById(R.id.edit_text_name);
                textDescription = (EditText) findViewById(R.id.edit_text_description);

                /* Generate Sequence object attribute values */
                String Organism = textOrganism.getText().toString();
                String Sequence_id = "";
                String Name = textName.getText().toString();
                String Description = textDescription.getText().toString();
                String Sequence = "";
                int Length = -1;
                int Num_features = -1;

                /* Generate Sequence object with attribute values gathered */
                Sequence sequence = new Sequence(Organism, Sequence_id, Name, Description, Sequence, Length, Num_features);

                /* Create parameters for request*/
                RequestParams params = new RequestParams();
                params.add("organism", sequence.getOrganism());
                params.add("name", sequence.getName());
                params.add("description", sequence.getDescription());

                /* HTTP POST */
                SequenceRestClient.post("requests", params, new AsyncHttpResponseHandler() {

                    /* Overrides default onSuccess call */
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Intent intent = new Intent(AddActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    /* Overrides default onFailure call */
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        System.out.println("Failed POST");
                    }
                });
            }
        });
    }
}
