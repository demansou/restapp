package edu.oregonstate.restapp.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Sequence {
    private String Organism;
    private String Sequence_id;
    private String Name;
    private String Description;
    private String Sequence;
    private String Length;
    private String Num_features;

    public Sequence(JSONObject object) {
        try {
            this.Organism = object.getString("organism");
            this.Sequence_id = object.getString("sequence_id");
            this.Name = object.getString("name");
            this.Description = object.getString("description");
            this.Sequence = object.getString("sequence");
            this.Length = object.getString("length");
            this.Num_features = object.getString("num_features");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Sequence(String Organism, String Sequence_id, String Name, String Description,
                    String Sequence, String Length, String Num_features) {
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

    public String getLength() {
        return this.Length;
    }

    public String getNumFeatures() {
        return this.Num_features;
    }
}
