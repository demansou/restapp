package edu.oregonstate.restapp.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Sequence {
    private String Organism;
    private String Sequence_id;
    private String Name;
    private String Description;
    private String Sequence;
    private int Length;
    private int Num_features;

    /**
     * Constructor for use when dealing with HTTP GET
     * @param object JSON object
     */
    public Sequence(JSONObject object) {
        try {
            this.Organism = object.getString("organism");
            this.Sequence_id = object.getString("sequence_id");
            this.Name = object.getString("name");
            this.Description = object.getString("description");
            this.Sequence = object.getString("sequence");
            this.Length = object.getInt("length");
            this.Num_features = object.getInt("num_features");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor for use when dealing with HTTP POST
     * @param Organism String
     * @param Sequence_id String
     * @param Name String
     * @param Description String
     * @param Sequence String
     * @param Length Integer
     * @param Num_features Integer
     */
    public Sequence(String Organism, String Sequence_id, String Name, String Description,
                    String Sequence, int Length, int Num_features) {
        this.Organism = Organism;
        this.Sequence_id = Sequence_id;
        this.Name = Name;
        this.Description = Description;
        this.Sequence = Sequence;
        this.Length = Length;
        this.Num_features = Num_features;
    }

    public String getOrganism() {
        return this.Organism;
    }

    public String getSequenceId() {
        return this.Sequence_id;
    }

    public String getName() {
        return this.Name;
    }

    public String getDescription() {
        return this.Description;
    }

    public String getSequence() {
        return this.Sequence;
    }

    public int getLength() {
        return this.Length;
    }

    public int getNumFeatures() {
        return this.Num_features;
    }
}
