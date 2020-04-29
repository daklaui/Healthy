package com.example.healthy.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthy.Classes.Food;
import com.example.healthy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodListeAdap extends RecyclerView.Adapter<MyViewHolder_this> {
    private Context context;
    private List<Food> foodlist;
    Activity activity;
    AlertDialog alertDialog;
    public FoodListeAdap(Context context, List<Food> foodlist,Activity activity) {
        super();
        this.context = context;
        this.foodlist = foodlist;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder_this onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_entry_view,parent,false);
        return new MyViewHolder_this(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_this holder, int position) {
        final Food myObject = foodlist.get(position);
        //holder.bind(myObject);
        holder.foodtitel.setText(myObject.getTitle());
        holder.foodcalories.setText(myObject.getCalories()+"Cal");
        holder.foodunite.setText("Unite: "+myObject.getUnite());
        Picasso.with(context).load(myObject.getImage()).fit().centerInside().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return foodlist.size();
    }


}
class MyViewHolder_this extends RecyclerView.ViewHolder {
    public TextView foodtitel, foodcalories, foodunite;
    public Button addfood;
    public ImageView imageView;
    // private ImageView imageView;

    //itemView est la vue correspondante à 1 cellule
    public MyViewHolder_this(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView
        foodtitel = itemView.findViewById(R.id.foodtitel);
        foodcalories = itemView.findViewById(R.id.foodcalories);
        foodunite = itemView.findViewById(R.id.foodunite);
        addfood = itemView.findViewById(R.id.addfood);
        imageView = itemView.findViewById(R.id.foodimage);

    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(final Food myObject) {
        // textViewView.setText(myObject);

        // Picasso.with(imageView.getContext()).load(myObject.getImageUrl()).centerCrop().fit().into(imageView);
    }
}