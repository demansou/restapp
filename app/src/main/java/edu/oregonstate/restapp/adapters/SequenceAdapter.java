package edu.oregonstate.restapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import edu.oregonstate.restapp.R;
import edu.oregonstate.restapp.models.Sequence;

import java.util.ArrayList;

public class SequenceAdapter extends ArrayAdapter<Sequence> {
    private static class ViewHolder {
        TextView organism;
        TextView sequence_id;
        TextView name;
        TextView description;
        TextView sequence;
        TextView length;
        TextView num_features;
    }

    public SequenceAdapter(Context context, ArrayList<Sequence> sequences) {
        super(context, R.layout.item_sequence, sequences);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Sequence sequence = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_sequence, parent, false);

            viewHolder.organism = (TextView) convertView.findViewById(R.id.value_sequence_organism);
            viewHolder.name = (TextView) convertView.findViewById(R.id.value_sequence_name);
            viewHolder.description = (TextView) convertView.findViewById(R.id.value_sequence_description);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.organism.setText(sequence.getOrganism());
        viewHolder.name.setText(sequence.getName());
        viewHolder.description.setText(sequence.getDescription());

        return convertView;
    }
}
