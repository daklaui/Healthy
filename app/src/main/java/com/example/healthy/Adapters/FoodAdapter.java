package com.example.healthy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthy.Classes.Food;
import com.example.healthy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<Food> foodlist;
    public FoodAdapter(Context context, List<Food> foodlist) {
        super();
        this.context = context;
        this.foodlist = foodlist;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_entry_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Food myObject = foodlist.get(position);
        //holder.bind(myObject);
        holder.foodtitel.setText(myObject.getTitle());
        holder.foodcalories.setText(myObject.getCalories());
        holder.foodunite.setText(myObject.getUnite());
        Picasso.with(context).load(myObject.getImage()).fit().centerInside().into(holder.imageView);
        holder.addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,myObject.getTitle(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public long getItemId(int position) {
       return foodlist.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return foodlist.size();
    }

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout relaytivelayout;
        if(convertView==null){
            relaytivelayout=(RelativeLayout) View.inflate(context, R.layout.food_entry_view, null);
        }
        else{
            relaytivelayout=(RelativeLayout) convertView;
        }
        Food curEntry = (Food)getItem(position);
        TextView fdname = relaytivelayout.findViewById(R.id.foodtitel);
        TextView fdqty = relaytivelayout.findViewById(R.id.foodcalories);
        TextView fdinfo = relaytivelayout.findViewById(R.id.foodunite);

       // fdname.setText(curEntry.getName());
       // fdinfo.setText(curEntry.getEnergy() + " cal");
//        fdqty.setText(curEntry.getQuantity()+"");

        return relaytivelayout;
    }*/
}


 class MyViewHolder extends RecyclerView.ViewHolder{
    public TextView foodtitel,foodcalories,foodunite;
     public  Button addfood;
     public ImageView imageView;
    // private ImageView imageView;

    //itemView est la vue correspondante à 1 cellule
    public MyViewHolder(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView
         foodtitel = itemView.findViewById(R.id.foodtitel);
         foodcalories = itemView.findViewById(R.id.foodcalories);
         foodunite = itemView.findViewById(R.id.foodunite);
         addfood=itemView.findViewById(R.id.addfood);
         imageView=itemView.findViewById(R.id.foodimage);

    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(final Food myObject){
       // textViewView.setText(myObject);

        // Picasso.with(imageView.getContext()).load(myObject.getImageUrl()).centerCrop().fit().into(imageView);
    }
}