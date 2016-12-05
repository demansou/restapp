package edu.oregonstate.restapp.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Cluster {

    /* Cluster object attributes */
    private String ClusterId;
    private String Organism;
    private String Name;
    private String NumberOfSequences;

    /**
     * Constructor for use when dealing with HTTP GET
     * @param object JSON object
     */
    public Cluster(JSONObject object) {
        try {
            String cluster = object.getString("cluster");
            JSONObject newObj = new JSONObject(cluster);
            this.ClusterId = newObj.getString("cluster_id");
            this.Organism = newObj.getString("organism");
            this.Name = newObj.getString("cluster_name");
            this.NumberOfSequences = object.getString("count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor for use when dealing with HTTP POST
     * @param Organism String
     * @param Name String
     * @param NumberOfSequences String
     */
    public Cluster(String Organism, String Name, String NumberOfSequences) {
        this.Organism = Organism;
        this.Name = Name;
        this.NumberOfSequences = NumberOfSequences;
    }

    /* Access to Cluster object attributes */
    public String getClusterId() { return this.ClusterId; }
    public String getOrganism() { return this.Organism; }
    public String getName() { return this.Name; }
    public String getNumberOfSequences() { return this.NumberOfSequences; }
}
