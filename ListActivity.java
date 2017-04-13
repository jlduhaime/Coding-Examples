package com.example.jorgetorres_aldana.quickbite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListActivity extends AppCompatActivity {



    //this contains the list of restaurants and their data
    //for name use:     places.get(<index>).get("place_name")
    //for address use:  places.get(<index>).get("vicinity")
    //for price use:    places.get(<index>).get("price_level")
    //for rating use:   places.get(<index>).get("rating")
    //for if open use:  places.get(<index>).get("open_now")
    private ArrayList<HashMap<String, String>> places;

    private double userLat, userLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        places = (ArrayList<HashMap<String,String>>) getIntent().getSerializableExtra("places");
        Bundle e = getIntent().getExtras();
        userLat = e.getDouble("userLat");
        userLng = e.getDouble("userLng");

        for(int i = 0; i < places.size(); i++) {

            if(places.get(i).get("vicinity") == null) {
                places.remove(i);
                continue;
            }
            else {
                Log.i("NAME: ", places.get(i).get("place_name"));
            }
        }

        
        ListView lv = (ListView)findViewById(R.id.restaurantList);
        lv.setAdapter(new MyListAdapter(this, R.layout.custom_list, places));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<HashMap<String,String>> {
        private int layout;
        private ArrayList<HashMap<String ,String>> mObjects;

        private MyListAdapter(Context context, int resources, ArrayList<HashMap<String,String>> objects) {
            super(context, resources, objects);
            mObjects = objects;
            layout = resources;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder mainViewHolder = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.name = (TextView)convertView.findViewById(R.id.nameField);
                viewHolder.address = (TextView)convertView.findViewById(R.id.addressField);
                viewHolder.rating = (TextView)convertView.findViewById(R.id.ratingField);
                viewHolder.price = (TextView)convertView.findViewById(R.id.priceField);
                viewHolder.favorite = (Button) convertView.findViewById(R.id.favorite);
                viewHolder.go = (Button) convertView.findViewById(R.id.goButton);

                convertView.setTag(viewHolder);
            }

            mainViewHolder = (ViewHolder) convertView.getTag();

            mainViewHolder.go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent gotoRestaurant = new Intent(ListActivity.this, MapsActivity.class);
                    Bundle b = new Bundle();
                    b.putString("place_name", places.get(position).get("place_name"));
                    b.putString("vicinity", places.get(position).get("vicinity"));
                    b.putString("lat", places.get(position).get("lat"));
                    b.putString("lng", places.get(position).get("lng"));
                    b.putString("rating", places.get(position).get("rating"));
                    b.putString("price_level", places.get(position).get("price_level"));
                    b.putDouble("userLat", userLat);
                    b.putDouble("userLon", userLng);

                    gotoRestaurant.putExtras(b);
                    startActivity(gotoRestaurant);
                }
            });

            final ViewHolder finalMainViewHolder = mainViewHolder;
            mainViewHolder.favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (finalMainViewHolder.favorite.getText().equals("FAV")) {
                        ContentValues values = new ContentValues();
                        values.put("Name", places.get(position).get("place_name"));
                        values.put("Address", places.get(position).get("vicinity"));
                        
                        places.get(position).put("restaurantFavorite", "true");

                        String col[] = {"Address"};
                        String idString = places.get(position).get("vicinity");
                        getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

                        Toast.makeText(ListActivity.this, "Favorite successsfully added",
                                Toast.LENGTH_LONG).show();
                        finalMainViewHolder.favorite.setText("UNFAV");

                    }
                        //if already favd
                    else if (finalMainViewHolder.favorite.getText().equals("UNFAV")) {                        
                        String addr = places.get(position).get("vicinity");
                        getContentResolver().delete(MyContentProvider.CONTENT_URI, "Address ='" + addr + "'"
                                , null);
                        places.get(position).put("restaurantFavorite", "false");

                        Toast.makeText(ListActivity.this, "Favorite successfully removed",
                                Toast.LENGTH_LONG).show();

                        finalMainViewHolder.favorite.setText("FAV");
                    }
                }
            });

            mainViewHolder.name.setText(places.get(position).get("place_name"));
            mainViewHolder.address.setText(places.get(position).get("vicinity"));
            mainViewHolder.rating.setText(places.get(position).get("rating"));
            mainViewHolder.price.setText(places.get(position).get("price_level"));


            return convertView;
        }
    }

    public class ViewHolder {
        TextView name, address, rating, price;
        Button favorite;
        Button go;
    }
}
