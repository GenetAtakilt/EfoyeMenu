package com.example.yilma.efoymenu;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yilma on 10-May-16.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<Food> dataSet;
    private Context mContext;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewFoodName;

        TextView textViewDescription;
        TextView getTextViewPrice;
        ImageView imageViewIcon;
        ImageView imageViewcat;
        Button orderbtn;
        CheckBox cbSelect;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewFoodName = (TextView) itemView.findViewById(R.id.textViewName);
            this.imageViewcat = (ImageView) itemView.findViewById(R.id.sorv_image_view);
            this.textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
            this.getTextViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            this.orderbtn = (Button)itemView.findViewById(R.id.orderbtn);
//            this.cbSelect = (CheckBox) itemView.findViewById(R.id.cbSelect);

        }
    }

    public CustomAdapter(Context mContext,ArrayList<Food> data) {
        this.mContext = mContext;
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        // view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        final Food food = dataSet.get(listPosition);

        TextView textViewName = holder.textViewFoodName;
        ImageView imageViewC = holder.imageViewcat;

        TextView textViewDescription = holder.textViewDescription;
        TextView textViewPrice = holder.getTextViewPrice;
        ImageView imageView = holder.imageViewIcon;
//        CheckBox cbSelected = holder.cbSelect;

        textViewName.setText(dataSet.get(listPosition).getFoodName());
        imageViewC.setImageResource(dataSet.get(listPosition).getCategory());
        textViewDescription.setText(dataSet.get(listPosition).getDescription());
        textViewPrice.setText(dataSet.get(listPosition).getPrice());
        imageView.setImageResource(dataSet.get(listPosition).getImage());
//        cbSelected.setOnCheckedChangeListener(null);
//        cbSelected.setChecked(dataSet.get(listPosition).isSelected());
//        cbSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                dataSet(isChecked);
//            }
//        });
//        holder.itemView.setOnClickListener(onClickListener(listPosition));
        holder.orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Intent intent = new Intent(mContext,OrderActivity.class);
                 int newTask = Intent.FLAG_ACTIVITY_NEW_TASK;
                 intent.putExtra("ordimage",dataSet.get(listPosition).getImage());
                intent.putExtra("name",dataSet.get(listPosition).getFoodName());
                intent.putExtra("description",dataSet.get(listPosition).getDescription());
                intent.putExtra("price",dataSet.get(listPosition).getPrice());
                //        intent.putExtra("price",furniture.getPrice());
                // intent.putExtra("name",furniture.getFurnitureName());
                 mContext.startActivity(intent);

            }
        });
    }

        @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
