package app.labs.idea.com.flymate;

        import java.io.BufferedInputStream;
        import java.io.InputStream;
        import java.util.ArrayList;
        import java.util.List;

        import org.apache.http.HttpResponse;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.util.ByteArrayBuffer;
        import org.json.JSONArray;
        import org.json.JSONObject;

        import android.app.ListActivity;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;

public class GooglePlacesExample extends ListActivity {
    ArrayList<GooglePlace> venuesList;

    // the google key

    // ============== YOU SHOULD MAKE NEW KEYS ====================//
    final String GOOGLE_KEY = "AIzaSyBb1fWUl5hwrXJiius9If627PULBxNuym0";

    // we will need to take the latitude and the logntitude from a certain point
    // this is the center of Chennai
    final String latitude = "13.0839";
    final String longtitude = "80.2700";

    ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_layout);

        // start the AsyncTask that makes the call for the venus search.
        new googleplaces().execute();
    }

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
                venuesList = (ArrayList<GooglePlace>) parseGoogleParse(temp);

                List<String> listTitle = new ArrayList<String>();

                for (int i = 0; i < venuesList.size(); i++) {
                    // make a list of the venus that are loaded in the list.
                    // show the name, the category and the city
                    listTitle.add(i, venuesList.get(i).getName() + "\nLat: " + venuesList.get(i).getLat() +"\nLng: " + venuesList.get(i).getLng());
                }

                // set the results to the list
                // and show them in the xml
                myAdapter = new ArrayAdapter<String>(GooglePlacesExample.this, R.layout.row_layout, R.id.listText, listTitle);
                setListAdapter(myAdapter);
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
