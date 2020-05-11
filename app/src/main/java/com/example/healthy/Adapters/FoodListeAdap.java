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

import com.app.progresviews.ProgressLine;
import com.example.healthy.Classes.Food;
import com.example.healthy.Classes.Regime;
import com.example.healthy.Database.DatabaseHandler;
import com.example.healthy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodListeAdap extends RecyclerView.Adapter<MyViewHolder_this> {
    private Context context;
    private List<Food> foodlist;
    String foodliist;
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

        holder.foodtitel.setText(myObject.getTitle());
        holder.foodcalories.setText(myObject.getCalories()+"Cal");
        holder.foodunite.setText("Unite: "+myObject.getUnite());
        Picasso.with(context).load(myObject.getImage()).fit().centerInside().into(holder.imageView);
        holder.addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseHandler databaseHandler=new DatabaseHandler(activity);
                databaseHandler.deleteTitle(myObject.getId());
                foodlist.remove(position);
                Regime regime=databaseHandler.getRegime(1);



                int cal;
                if(regime.getTyp_regime().contains("GA"))
                {
                    if(regime.getObjectif()==1)
                    {
                        cal=databaseHandler.getDiet(6).getCalories();
                    }
                    else
                    {
                        cal=databaseHandler.getDiet(7).getCalories();
                    }
                }
                else
                if(regime.getTyp_regime().contains("PERDER"))
                {
                    if(regime.getObjectif()==1)
                    {
                        cal=databaseHandler.getDiet(1).getCalories();
                    }
                    else if(regime.getObjectif()==2)
                    {
                        cal=databaseHandler.getDiet(2).getCalories();
                    }
                    else if(regime.getObjectif()==3)
                    {
                        cal=databaseHandler.getDiet(3).getCalories();
                    }
                    else
                    {
                        cal=databaseHandler.getDiet(4).getCalories();
                    }
                }
                else
                {

                    cal=databaseHandler.getDiet(5).getCalories();
                }



                ProgressLine progressLine = activity.findViewById(R.id.progress_line);

                int sum=0;
                for(int i=0;i<foodlist.size();i++)
                {
                    sum+=foodlist.get(i).getCalories();
                }


             notifyItemChanged(position);
                int pourcentage= (int) ((100*Math.abs(sum)/cal));
                progressLine.setmValueText(String.valueOf((cal-sum)));
                progressLine.setmPercentage(pourcentage);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodlist.size();
    }


}
class MyViewHolder_this extends RecyclerView.ViewHolder {
    public TextView foodtitel, foodcalories, foodunite;
    public Button addfood;
    //image view
    public ImageView imageView;
    // private ImageView imageView;

    //itemView is the view of the corresponding cell
    public MyViewHolder_this(View itemView) {
        super(itemView);

        //set vie w holder
        foodtitel = itemView.findViewById(R.id.foodtitel);
        foodcalories = itemView.findViewById(R.id.foodcalories);
        foodunite = itemView.findViewById(R.id.foodunite);
        addfood = itemView.findViewById(R.id.addfood);
        imageView = itemView.findViewById(R.id.foodimage);
        addfood.setText("Remove");


    }

    //add function to add new obejct
    public void bind(final Food myObject) {

    }
}