package edu.oregonstate.restapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import edu.oregonstate.restapp.R;
import edu.oregonstate.restapp.models.Cluster;

import java.util.ArrayList;

public class ClusterAdapter extends ArrayAdapter<Cluster> {
    private static class ViewHolder {
        TextView cluster_id;
        TextView cluster_name;
        TextView organism;
        TextView num_features;
    }

    public ClusterAdapter(Context context, ArrayList<Cluster> clusters) {
        super(context, R.layout.item_cluster, clusters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cluster cluster = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_cluster, parent, false);

            viewHolder.cluster_id = (TextView) convertView.findViewById(R.id.value_cluster_id);
            viewHolder.cluster_name = (TextView) convertView.findViewById(R.id.value_cluster_name);
            viewHolder.organism = (TextView) convertView.findViewById(R.id.value_organism);
            viewHolder.num_features = (TextView) convertView.findViewById(R.id.value_num_sequences);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.cluster_id.setText(cluster.getClusterId());
        viewHolder.cluster_name.setText(cluster.getName());
        viewHolder.organism.setText(cluster.getOrganism());
        viewHolder.num_features.setText(cluster.getNumberOfSequences());

        return convertView;
    }
}
