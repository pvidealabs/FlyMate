package app.labs.idea.com.flymate;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private HashMap<String,ArrayList<GooglePlace>> locationMap;


    // ============== YOU SHOULD MAKE NEW KEYS ====================//
    final String GOOGLE_KEY = "AIzaSyBb1fWUl5hwrXJiius9If627PULBxNuym0";

    // we will need to take the latitude and the logntitude from a certain point
    // this is the center of Chennai
    final String latitude = "13.0839";
    final String longtitude = "80.2700";
    final String type1 = "hospital";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded(null);

        locationMap = new HashMap<String,ArrayList<GooglePlace>>();

        ArrayList<GooglePlace> hotels = new ArrayList<>();



        ArrayList<GooglePlace> atms = new ArrayList<>();


        ArrayList<GooglePlace> hospitals = new ArrayList<>();


        
        locationMap = new HashMap<>();
        locationMap.put("hospital" , hospitals);
        locationMap.put("atm", atms);
        locationMap.put("hotel",hotels);

        final Button hospitalButton = (Button)findViewById(R.id.hospital);
        hospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new googleplaces().execute();
                //setUpMapIfNeeded(locationMap.get("hospital"));
            }
        });


        final Button restaurantButton = (Button)findViewById(R.id.restaurant);
        restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpMapIfNeeded(locationMap.get("hotel"));
            }
        });

        final Button atmButton = (Button)findViewById(R.id.atm);
        atmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpMapIfNeeded(locationMap.get("atm"));
            }
        });

        }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded(null);
    }




    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call  once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded(ArrayList<GooglePlace> locationDetails) {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap(locationDetails);
            }
        }
        else
            setUpMap(locationDetails);
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */

    private static final LatLng CHENNAI = new LatLng(13.0839, 80.2700);


    private void setUpMap(ArrayList<GooglePlace> locationDetails) {
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        mMap.addMarker(new MarkerOptions().position(CHENNAI).title("CHENNAI"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CHENNAI, 20));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);

        if(locationDetails != null) {
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(CHENNAI).title("CHENNAI"));
            for(GooglePlace locationDetail : locationDetails){
                mMap.addMarker(new MarkerOptions().position(new LatLng(locationDetail.getLat(),locationDetail.getLng())).title(locationDetail.getName()));
            }
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
            mMap.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);
        }

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener()
        {
            @Override
            public boolean onMyLocationButtonClick()
            {
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                mMap.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);
                return false;
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void goToHome(View view){

        Intent mainIntent = new Intent(this,MainActivity.class);
        ImageButton mainPlan = (ImageButton) findViewById(R.id.trvlHme);

     if(Build.VERSION.SDK_INT == 21)   {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, mainPlan, "mapHome");
        startActivity(mainIntent,options.toBundle());

    }
    else{

        startActivity(mainIntent);
    }

    }



    //place api

    private class googleplaces extends AsyncTask<View, Void, String> {

        String temp;

        @Override
        protected String doInBackground(View... urls) {
            // make Call to the url
            temp = makeCall("https://maps.googleapis.com/maps/api/place/search/json?location=" + latitude + "," + longtitude + "&radius=4000&types=hospital&sensor=true&key=" + GOOGLE_KEY );

            //print the call in the console
            System.out.println("https://maps.googleapis.com/maps/api/place/search/json?location=" + latitude + "," + longtitude + "&radius=4000&types=hospital&sensor=true&key=" + GOOGLE_KEY);
            return "";
        }

        @Override
        protected void onPreExecute() {
            // we can start a progress bar here
        }

        @Override
        protected void onPostExecute(String result) {
            if (temp == null) {
                // we have an error to the call
                // we can also stop the progress bar
            } else {
                // all things went right

                // parse Google places search result
                ArrayList<GooglePlace> venuesList = (ArrayList<GooglePlace>) parseGoogleParse(temp);

                List<String> listTitle = new ArrayList<String>();

                for (int i = 0; i < venuesList.size(); i++) {
                    // make a list of the venus that are loaded in the list.
                    // show the name, the category and the city
                    listTitle.add(i, venuesList.get(i).getName() + "\nLat: " + venuesList.get(i).getLat() +"\nLng: " + venuesList.get(i).getLng());
                }

                HashMap<String,ArrayList<GooglePlace>> locationMap;

                locationMap = new HashMap<>();
                locationMap.put("hospital" , venuesList);



                setUpMapIfNeeded(locationMap.get("hospital"));

                // set the results to the list
                // and show them in the xml
                //myAdapter = new ArrayAdapter<String>(GooglePlacesExample.this, R.layout.row_layout, R.id.listText, listTitle);
                //setListAdapter(myAdapter);
            }
        }
    }

    public static String makeCall(String url) {

        // string buffers the url
        StringBuffer buffer_string = new StringBuffer(url);
        String replyString = "";

        // instanciate an HttpClient
        HttpClient httpclient = new DefaultHttpClient();
        // instanciate an HttpGet
        HttpGet httpget = new HttpGet(buffer_string.toString());

        try {
            // get the responce of the httpclient execution of the url
            HttpResponse response = httpclient.execute(httpget);
            InputStream is = response.getEntity().getContent();

            // buffer input stream the result
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(20);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            // the result as a string is ready for parsing
            replyString = new String(baf.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(replyString);

        // trim the whitespaces
        return replyString.trim();
    }

    private static ArrayList<GooglePlace> parseGoogleParse(final String response) {

        ArrayList<GooglePlace> temp = new ArrayList<GooglePlace>();
        try {

            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("results")) {

                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    GooglePlace poi = new GooglePlace();
                    if (jsonArray.getJSONObject(i).has("name")) {
                        poi.setName(jsonArray.getJSONObject(i).optString("name"));
                        //poi.setRating(jsonArray.getJSONObject(i).optString("lag", " "));

                        if (jsonArray.getJSONObject(i).has("geometry")) {
                            if(jsonArray.getJSONObject(i).getJSONObject("geometry").has("location")) {
                                if (jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").has("lat")) {
                                    poi.setLat(jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").optDouble("lat"));
                                }
                                if (jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").has("lng")) {
                                    poi.setLng(jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").optDouble("lng"));
                                }
                            }
                        } else {
                            poi.setLat(0);
                            poi.setLng(0);
                        }

                    }
                    temp.add(poi);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<GooglePlace>();
        }
        return temp;

    }


}
