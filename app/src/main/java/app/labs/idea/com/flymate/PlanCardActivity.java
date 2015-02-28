package app.labs.idea.com.flymate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class PlanCardActivity extends Activity {

    private List<RowItem> rowItems;
    private List<FlightDetails> flightItems;

    private static Integer[] chnImages = {
            R.drawable.crocodile_park,
            R.drawable.mahabalipuram,
            R.drawable.marina_beach,
            R.drawable.pondicherry,
            R.drawable.vandaloor_zoo
    };

    private static Integer[] nzImages = {
            R.drawable.bay_of_islands,
            R.drawable.franz_josef_glacier,
            R.drawable.milford_sound,
            R.drawable.rotorua,
            R.drawable.tongariro
    };

    private static Integer[] ibziaImages = {
            R.drawable.atlantis,
            R.drawable.formentera,
            R.drawable.port_of_ibiza,
            R.drawable.ses_variades,
            R.drawable.watersports
    };

    private static String[] ibziaDescription = {
            "The area known as Atlantis is the site of a former quarry – the site where the rocks that form the basis of the Old Town where all sourced from here. The old quarry site has now been totally reclaimed by the sea and at first glance appears to be a whole town completely underwater. It is opposite the mystical island of Es Vedra and is imbued with some of the magic of the area. It is an excellent place to take some unique photographs as the scenery is stunning and quite picturesque. Who knows, perhaps you might catch a glimpse of a mermaid or siren flitting about the edge of your vision!",
            "A narrow strait of water separates the island of Formentera from Ibiza’s southern coastline. Home to a number of pristine beaches, most of which are considered as ‘naturist beaches’, the island allows nude sunbathing and swimming so if that is something you have always wanted to try, why not give it a go on Formentera. The island is also a favorite destination and stopping point for yachts and sailboats cruising to and from Ibiza and Formentera.",
            "As a change from the hedonistic atmosphere of the many luxurious resorts and popular beachfront strips of Ibiza, why not take a leisurely stroll around the Port of Ibiza and watch the various cruise ships, fishing boats and luxury yachts come and go. The lively banter and expertise of long-time sailors is a joy to observe and who knows, it may even inspire you to go for a sailing jaunt across the port! Have lunch at the many wonderful restaurants and cafes found throughout the Port or simply while away the afternoon while sipping a refreshing sangria at the promenade to cap off a blissful conclusion to your afternoon.",
            "The Ses Variades coast gives a superb vantage point from which to watch the sunset, and large crowds of people make a point of being lined comfortably on the shore to watch the stunning play of color in the sky as the sun slowly slips beneath the horizon, turning the waters a brilliant, fiery orange.",
            "Ibiza’s clear, blue waters are an irresistible temptation for those eager to try out the island’s many watersports options. Everyone can join in and have fun, no restrictions! Beginners, watersports enthusiasts and yes, even professionals are all welcome here. Popular watersports include jetskiing, parasailing, windsurfing (when winds are a bit stronger come winter), wakeboarding, scuba diving, deep sea fishing and kayak excursions just to name a few. Swimming and snorkelling are also highly recommended for children and families out on a day at the beach. The waters surrounding Ibiza’s beaches are fairly shallow, with a sandy bottom, making it safe for children to have fun swimming and splashing around at the beach."
    };

    private static String[] chnDescription = {
            "Originally started with a more serious conservation point of view, Crocodile Bank is now the weekend spot for a fascinating day of herpetology. For the kids, it’s a special treat to see large crocs spend the day languorously by the water. The Madras Crocodile Bank Trust and Centre for Herpetology also allows adults to volunteer and gain hands-on experience in the field.",
            "Just 45 minutes out of the city, the East Coast Road first stops at the historic town of Mahabalipuram. The 7th century erstwhile port city is famous for its rock-cut shore temples. A lone lighthouse on a hill watches over a group of temples below. Mahabalipuram also has some great cafes that serve good sea food. A backpacker’s delight, one can find cheap accommodations and plenty of activities.",
            "This expansive beach is Chennai's most famous tourist attraction, though the undercurrent is too strong for all but the strongest swimmers. Look out for the \"Kannagi\" statue on Marina Beach, which tells the story of the legendary character from a South Indian epic.",
            "Pondicherry, recently renamed as Puducherry, is a town tucked away on the Eastern seaboard of India. Among its diverse attractions are a coastline of 32 km, palm-fringed beaches, backwaters, fishing villages, beach resorts, the Sri Aurobindo Ashram, the international city of Auroville, the French boulevard town with its French heritage, and so on. Some great food, some fine wine, and a great adventure await you here.",
            "This is certainly one of the best zoos in India, having a vast area where animals are kept in a natural environment. They have a lion safari. It is plastic free zone. Great for children."
    };

    private static String[] nzDescription = {
            "The Bay of Islands is one of the most popular holiday destinations in New Zealand. The picturesque area contains 144 islands, many secluded bays and some great sandy beaches. This beautiful bay has an abundance of marine life including whales, penguins, dolphins and the big marlin. Not surprisingly, it is a popular tourist spot for sailing yachts on world cruises and international sport fishermen.",
            "This glacier, located within Westland National Park in the southwest, is one of the world’s most accessible. Visitors can walk right up to the foot of the massive glacier or take a helicopter ride over the dazzling Ice Age remnant. Together with Fox Glacier it is one of South Westland’s major drawcards for tourists.",
            "Milford Sound is among the most famous tourist attractions in New Zealand. Lying at the most northern and accessible end of Fiordland National Park, Milford sound offers some of the world’s most staggering coastal scenery with its dramatic peaks and dark blue waters. The area’s frequent downpours only enhance this South Island beauty, sending numerous waterfalls cascading down the cliffs.",
            "Rotorua is known as the thermal wonderland of New Zealand. There are numerous geysers and hot springs in and around the city. Many of these are in parks and reserves. Natural eruptions of steam, hot water and mud occasionally occur in new locations. Nearby Wai-O-Tapu is also a popular tourist attraction with many hot springs noted for their colorful appearance, in addition to the Lady Knox Geyser.",
            "The first national park of New Zealand, Tongariro is known for its surprises and extremes. The park’s diverse range of ecosystems includes tranquil lakes, active volcanoes, herb fields, untamed forests and desert-like plateaus. Start your trek at the Whakapapa Visitor Center, just a three hour hike from the stunning Taranaki Falls. The short hike will take you through scrubland and forest and across the lava line of volcanic eruptions from hundreds of years ago."
    };

    private static String[] nzTitles = {
            "Bay of Islands",
            "Franz Josef Glacier",
            "Milford Sound",
            "Rotorua",
            "Tongariro"
    };

    private static String[] chnTitles = {
        "Crocodile Park",
        "Mahabalipuram",
        "Marina Beach",
        "Pondicherry",
        "Vandaloor Zoo"
    };

    public static String[] ibziaTitles = {
        "Atlantis",
        "Formentera",
        "Port of Ibiza",
        "Ses Variades",
        "Water Sports"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_card);
        Intent intent = getIntent();
        String[] titles = null,des = null;
        Integer[] images = null;
        String maxT,currT,minT;
        Integer weatherImg = null;
        String weatherStr = null;

        String weatherHd = "Weather ";
        String placesHd = "Places to visit";
        String flightsHd = "Flights to ";
        String placeCode = intent.getStringExtra(PlanActivity.DEST_CITY);
        String depCode = intent.getStringExtra(PlanActivity.DEPT_CITY);
        String date = intent.getStringExtra(PlanActivity.SEL_DATE);
        //String placeCode = "MAA"; //AKL,IBZ
        if(placeCode.equals("MAA")){
            images = chnImages;
            titles = chnTitles;
            des =chnDescription;
            maxT = "29";
            minT = "22";
            currT = "27";
            weatherStr = "Sunny";
            weatherImg = R.drawable.sunny;
            weatherHd += "Chennai";
            flightsHd += "Chennai";
        }

        else if(placeCode.equals("AKL")){
            images = nzImages;
            titles = nzTitles;
            des = nzDescription;
            maxT = "21";
            minT = "16";
            currT = "19";
            weatherStr = "Rainy";
            weatherImg = R.drawable.rain;
            weatherHd += "Auckland";
            flightsHd += "Auckland";
        }

        else{
            images = ibziaImages;
            titles = ibziaTitles;
            des =ibziaDescription;
            maxT = "16";
            minT = "5";
            currT ="10";
            weatherStr = "Heavy Hail";
            weatherImg = R.drawable.hail_heavy;
            weatherHd += "Ibiza";
            flightsHd += "Ibiza";
        }

        TextView weatherHD = (TextView) findViewById(R.id.weatherHd);
        weatherHD.setText(weatherHd);

        TextView placeHD = (TextView) findViewById(R.id.placesHd);
        placeHD.setText(placesHd);

        TextView flightHD = (TextView) findViewById(R.id.flightHd);
        flightHD.setText(flightsHd);

        TextView temp = (TextView) findViewById(R.id.currTemp);
        temp.setText(currT+ (char) 0x00B0 + "C");

        TextView maxTemp = (TextView) findViewById(R.id.maxTemp);
        maxTemp.setText(maxT+ (char) 0x00B0 + "C");

        TextView minTemp = (TextView) findViewById(R.id.minTemp);
        minTemp.setText(minT+ (char) 0x00B0 + "C");

        TextView weatherString = (TextView) findViewById(R.id.weatherStr);
        weatherString.setText(weatherStr);

        ImageView weatherImage = (ImageView) findViewById(R.id.weatherIcon);
        weatherImage.setImageResource(weatherImg);


        ListView lv = (ListView) findViewById(R.id.myList);
        rowItems = new ArrayList<RowItem>();

        //Populate the List
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(images[i], titles[i], des[i]);
            rowItems.add(item);
        }

        // Set the adapter on the ListView
        LazyAdapter adapter = new LazyAdapter(getApplicationContext(), R.layout.list_row, rowItems);
        lv.setAdapter(adapter);

        ListView fghtView = (ListView) findViewById(R.id.flights);
        flightItems = new ArrayList<FlightDetails>();

        for(int j=0;j<5;j++){
            FlightDetails flightDetails = new FlightDetails();
            flightDetails.setTimings("09:40pm-12:30pm(+1)");
            flightDetails.setFlightNames("Air France, Air Asia");
            flightDetails.setDuration("37h 45m");
            flightDetails.setStops("2 stops");
            flightDetails.setStopCities("MAA, FRA, IBZ");
            flightItems.add(flightDetails);
        }
        FlightDetailsAdapter flightDetailsAdapter = new FlightDetailsAdapter(getApplicationContext(),R.layout.weather_card,flightItems);
        fghtView.setAdapter(flightDetailsAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void goBack(View view){

        Intent mainIntent = new Intent(this,PlanActivity.class);
        ImageButton mainPlan = (ImageButton) findViewById(R.id.bckBtn);

        if(Build.VERSION.SDK_INT == 21) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, mainPlan, "planTrans");
            startActivity(mainIntent, options.toBundle());
        }
        else{

            startActivity(mainIntent);
        }

    }
}
