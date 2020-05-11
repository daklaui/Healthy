package com.example.healthy.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthy.Classes.Food;
import com.example.healthy.Database.DatabaseHandler;
import com.example.healthy.Activities.ListeOfFood;
import com.example.healthy.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<Food> foodlist;
    Activity activity;
    AlertDialog alertDialog;

    public FoodAdapter(Context context, List<Food> foodlist,Activity activity) {
        super();
        this.context = context;
        this.foodlist = foodlist;
        this.activity = activity;
    }

//cretae view holder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_entry_view,parent,false);
        return new MyViewHolder(view);
    }
// match the template withe the corresponding class
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Food myObject = foodlist.get(position);
        //holder.bind(myObject);
        holder.foodtitel.setText(myObject.getTitle());
        holder.foodcalories.setText(myObject.getCalories()+"Cal");
        holder.foodunite.setText("Unite: "+myObject.getUnite());
        Picasso.with(context).load(myObject.getImage()).fit().centerInside().into(holder.imageView);
        holder.addfood.setOnClickListener(new View.OnClickListener() {
            //set click action
            @Override
            public void onClick(View v) {
                final DatabaseHandler databaseHandler=new DatabaseHandler(activity);
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                LayoutInflater inflater = activity.getLayoutInflater();
                View mview=  inflater.inflate(R.layout.addfood_layout,null);

                /********************************************************/

                final EditText foodval=mview.findViewById(R.id.food_newVal);
                Button confirme=mview.findViewById(R.id.btnaddfoddlocal);
                Button cancel=mview.findViewById(R.id.btnCancelloCal);
                TextView TitreFood=mview.findViewById(R.id.TitreFood);
                TextView Unite=mview.findViewById(R.id.Unite_Add);
                /********************************************************/

                TitreFood.setText(myObject.getTitle());
                Unite.setText("Unite : "+myObject.getCalories()+" cal/ "+myObject.getQnparUnite()+"/"+ myObject.getUnite());


                confirme.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final double Quntité=Double.parseDouble(foodval.getText().toString());
                        Food food = new Food();
                        int nomberdecalories = 0;
                        Log.e("TableMoataz", myObject.getQnparUnite()+"/"+myObject.getCalories()+"/"+Quntité);
                        switch(myObject.getUnite())
                        {
                            case "g" : nomberdecalories=(int)Math.round(Quntité*(myObject.getCalories()/Double.parseDouble(myObject.getQnparUnite())));break;
                            case "ml" : nomberdecalories=(int)Math.round(Quntité*(myObject.getCalories()/Double.parseDouble(myObject.getQnparUnite())));break;
                            default:nomberdecalories=(int)Math.round(Quntité*myObject.getCalories());
                        }

                        food.setCalories(nomberdecalories);
                        food.setTitle(myObject.getTitle());
                        food.setUnite(myObject.getUnite());
                        food.setQnparUnite(String.valueOf(Quntité));
                        food.setImage(myObject.getImage());
                        if(myObject.getDate()=="")
                        {
                            food.setDate(getDay());
                        }
                    else
                        {
                            food.setDate(myObject.getDate());
                        }

                        databaseHandler.addFood(food);
                       Intent intent = new Intent(activity, ListeOfFood.class);
                      activity.startActivity(intent);

                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });

                builder.setView(mview);
                builder.setCancelable(false);
                alertDialog=builder.create();
                alertDialog.show();

            }
        });
    }
// return date
    private String getDay() {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        month=month+1;
        String day_1="";
        String Month_1="";
        if(day<10)
        {
            day_1="0"+day;

        }
        else
        {
            day_1=String.valueOf(day);
        }
        if(month<10)
        {
            Month_1="0"+month;
        }
        else
        {
            Month_1=String.valueOf(month);
        }
        return day_1+"-"+Month_1+"-"+year;
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

    //itemView corresponding to the view of the cell
    public MyViewHolder(View itemView) {
        super(itemView);

        //
         foodtitel = itemView.findViewById(R.id.foodtitel);
         foodcalories = itemView.findViewById(R.id.foodcalories);
         foodunite = itemView.findViewById(R.id.foodunite);
         addfood=itemView.findViewById(R.id.addfood);
         imageView=itemView.findViewById(R.id.foodimage);

    }

    public void bind(final Food myObject){



    }


}