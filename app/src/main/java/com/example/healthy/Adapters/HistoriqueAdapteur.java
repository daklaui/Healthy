package com.example.healthy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.healthy.Classes.Historique_Regime;
import com.example.healthy.R;

import java.util.ArrayList;

public class HistoriqueAdapteur  extends ArrayAdapter<Historique_Regime> {
    private ArrayList<Historique_Regime> dataSet;
    Context mContext;
    private static class ViewHolder {
        TextView Date_historique;
        TextView Poids_histoeique;
        TextView EvolutionHistorique;
        TextView IMC_Historqie;
    }

    public HistoriqueAdapteur(ArrayList<Historique_Regime> data, Context context) {
        super(context, R.layout.templatehistorique, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Historique_Regime dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.templatehistorique, parent, false);
            viewHolder.Date_historique = (TextView) convertView.findViewById(R.id.Date_historique);
            viewHolder.Poids_histoeique = (TextView) convertView.findViewById(R.id.Poids_histoeique);
            viewHolder.EvolutionHistorique = (TextView) convertView.findViewById(R.id.EvolutionHistorique);
            viewHolder.IMC_Historqie = (TextView) convertView.findViewById(R.id.IMC_Historqie);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

       // Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
     //   result.startAnimation(animation);
      //  lastPosition = position;

        viewHolder.Date_historique.setText(dataModel.getDate());
        viewHolder.Poids_histoeique.setText(dataModel.getNew_Poids()+"");
        viewHolder.EvolutionHistorique.setText(dataModel.getEvolution()+"");
        viewHolder.IMC_Historqie.setText(dataModel.getNew_IMC()+"");
        // Return the completed view to render on screen
        return convertView;
    }
}
