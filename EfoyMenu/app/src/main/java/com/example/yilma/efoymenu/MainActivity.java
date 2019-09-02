package com.example.yilma.efoymenu;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static View.OnClickListener myOnClickListener;
    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;
    private static ArrayList<Food> data;
    private static ArrayList<Integer> removedItems;
    public TextView mTitle;
    private RecyclerView.LayoutManager layoutManager;
    public static Dialog d;
    public static Context c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.in, R.anim.out);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Get access to the custom title view
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/orange.ttf");
        mTitle.setTypeface(custom_font);


        myOnClickListener = new MyOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Bundle extras = getIntent().getExtras();

        String foodval = extras.getString("key");
        switch (foodval){
            case "Pizza":
                data = new ArrayList<Food>();
                for (int i = 0; i < MyData.pizzaname.length; i++) {
                    data.add(new Food(
                            MyData.pizzaname[i],
                            MyData.category[i],
                            MyData.pizzadescription[i],
                            MyData.pizzaimg[i],
                            MyData.pizzaprice[i]
                    ));
                }
                mTitle.setText(foodval);
                break;
            case "Hot Beverages":
                data = new ArrayList<Food>();
                for (int i = 0; i < MyData.hotname.length; i++) {
                    data.add(new Food(
                            MyData.hotname[i],
                            MyData.hotcategory[i],
                            MyData.hotdescription[i],
                            MyData.hotimg[i],
                            MyData.hotprice[i]
                    ));
                }
                mTitle.setText(foodval);
                break;
            case "Shots":
                data = new ArrayList<Food>();
                for (int i = 0; i < MyData.shotname.length; i++) {
                    data.add(new Food(
                            MyData.shotname[i],
                            MyData.hotcategory[i],
                            MyData.shotdescription[i],
                            MyData.shotimg[i],
                            MyData.shotprice[i]
                    ));
                }
                mTitle.setText(foodval);
                break;
            case "Salads":
                data = new ArrayList<Food>();
                for (int i = 0; i < MyData.saladname.length; i++) {
                    data.add(new Food(
                            MyData.saladname[i],
                            MyData.hotcategory[i],
                            MyData.saladdescription[i],
                            MyData.saladimg[i],
                            MyData.saladprice[i]
                    ));
                }
                mTitle.setText(foodval);
        }

        removedItems = new ArrayList<Integer>();

        adapter = new CustomAdapter(this,data);
        recyclerView.setAdapter(adapter);
    }


    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;
        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            removeItem(v);
        }

        private void openDialog(View v){

            d = new Dialog(c);
            d.requestWindowFeature(Window.FEATURE_NO_TITLE);
            d.setContentView(R.layout.new_card);
            d.show();
        }

        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
            String selectedName = (String) textViewName.getText();
            int selectedItemId = -1;
            for (int i = 0; i < MyData.pizzaname.length; i++) {
                if (selectedName.equals(MyData.pizzaname[i])) {
                    selectedItemId = MyData.id_[i];
                }
            }
            removedItems.add(selectedItemId);
            data.remove(selectedItemPosition);
            adapter.notifyItemRemoved(selectedItemPosition);
        }
    }
}
