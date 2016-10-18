package com.example.amrit.cmchguide.Map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.amrit.cmchguide.Map.Building;
import com.example.amrit.cmchguide.R;
import com.qozix.tileview.TileView;
import com.qozix.tileview.markers.MarkerEventListener;

public class MapTileActivity extends AppCompatActivity {

    TileView tileView;
    TileView tileView001;
    LinearLayout linearLayout;
    Spinner spinner;
    String[] buildingArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_map_tile);

        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(linearLayout);

        spinner = new Spinner(this);
        LinearLayout.LayoutParams spinnerViewLayout = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(spinner, spinnerViewLayout);

        tileView = new TileView(this);

        //create tileview -- Amrit
        tileView.setSize(1368, 1600);

        //Setting the minimum parameters from the documentation -- Amrit
        tileView.addDetailLevel(1f, "tiles/1000_%col%_%row%.png", "samples/cu.jpg");
        tileView.addDetailLevel(0.5f, "tiles/500_%col%_%row%.png", "samples/cu.jpg");
        tileView.addDetailLevel(0.25f, "tiles/250_%col%_%row%.png", "samples/cu.jpg");
        tileView.addDetailLevel(0.125f, "tiles/125_%col%_%row%.png", "samples/cu.jpg");

        tileView.moveToAndCenter(1368, 1600);
        tileView.slideToAndCenter(1368, 1600);
        tileView.setScale(0.25);

        LinearLayout.LayoutParams tileViewLayout = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);

        //Add the view to display it -- Amrit
        linearLayout.addView(tileView, tileViewLayout);
        //setContentView(tileView);

        tileView.addTileViewEventListener(new TileView.TileViewEventListenerImplementation(){
            @Override
            public void onTap(int x, int y) {
                toastIt("The co-ordinate of this place is: " + x + ", " +y);
            }
        });

        tileView001 = new TileView(MapTileActivity.this);

        tileView001.setSize(3236, 2461);
        tileView001.addDetailLevel(1f, "tiles001/1000_%col%_%row%.gif", "samples001/map.gif");
        tileView001.addDetailLevel(0.5f, "tiles001/500_%col%_%row%.gif", "samples001/map.gif");
        tileView001.addDetailLevel(0.25f, "tiles001/250_%col%_%row%.gif", "samples001/map.gif");
        tileView001.addDetailLevel(0.125f, "tiles001/125_%col%_%row%.gif", "samples001/map.gif");

        tileView001.addTileViewEventListener(new TileView.TileViewEventListenerImplementation(){
            @Override
            public void onTap(int x, int y) {
                toastIt("The co-ordinate of this place is: " + x + ", " +y);
            }
        });

        initialApp();
    }

    private void initialApp() {

        //buildingArray = getResources().getStringArray(R.array.buildings);
        //ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
        //        buildingArray);
        ArrayAdapter<Building> spinnerAdapter = new ArrayAdapter<Building>(this, android.R.layout.simple_spinner_item,
                new Building[]{
                        new Building("VC Hill", 520, 1100),
                        new Building("Zia Hall", 750, 980),
                        new Building("Arts Faculty", 450, 800)
                });
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Building building = (Building) parent.getSelectedItem();
                toastIt("You seleceted Item #" + building.toString());
                positionMap(building);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ImageView markerA = new ImageView(this);
        markerA.setImageResource(R.drawable.marker_blue);
        markerA.setTag("Arts Faculty");

        ImageView markerB = new ImageView(this);
        markerB.setImageResource(R.drawable.marker_blue);
        markerB.setTag("VC Hill");

        ImageView markerC = new ImageView(this);
        markerC.setImageResource(R.drawable.marker_blue);
        markerC.setTag("Zia Hall");

        tileView.addMarker(markerA, 450, 800, -0.5f, -1.0f);
        tileView.addMarker(markerB, 520, 1100, -0.5f, -1.0f);
        tileView.addMarker(markerC, 750, 980, -0.5f, -1.0f);

        tileView.addMarkerEventListener(new MarkerEventListener() {
            @Override
            public void onMarkerTap(View view, int i, int i1) {
                Log.d("Marker Event", "marker tag = " + view.getTag());

                //Switch out the tileview -- Amrit
                linearLayout.removeView(tileView);

                LinearLayout.LayoutParams tileViewLayout = new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);

                //Add the view to display it -- Amrit
                linearLayout.addView(tileView001, tileViewLayout);
                tileView001.setScale(0.50);

            }
        });
    }

    private void positionMap(Building building) {
        tileView.slideToAndCenter(building.x, building.y);
        tileView.setScale(1f);
    }

    private void toastIt(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
