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
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class AssistActivity extends Activity {

    private String checkListStr = "1. Passport/Licence\n"+
            "2. Brush/ToothPaste/Towel\n"+
            "3. Pajamas/Sleepwear/Innerwear\n"+
            "4. Sunscreen lotion/Shaving gel/moisturizer\n"+
            "5. Deodorant/Razor/Haircomb\n"+
            "6. Extra batteries\n"+
            "7. Digital camera/Headphones\n"+
            "8. Shoes/Socks/Shoe Polish\n"+
    "9. Debit/Credit cards\n";

    private String faqStr = "Q. How to handle Superficial Skin Infections?\n"+
            "A. Cover the area with a clean, dry dressing that gets changed at least twice per day. Clean the area often, again with soap and water.\n\n"+
            "Q. How to handle Mosquito Bites?\n"+
            "A. Use mosquito net or mosquito repellent\n\n"+
            "Q. How to handle Jet Lag?\n"+
            "A. Caffeine can help give a boost, allowing you to stay up a bit more or feel less fatigued.\n\n"+
            "Q. How to handle Tooth Injuries?\n"+
            "A. Warm salt water rinses can help keep problem areas clean, and then you should start looking for a dentist. Mouth infections can be very painful and dangerous. Don’t delay treatment.\n\n"+
            "Q. How to handle Respiratory Infections?\n"+
            "A. Staying hydrated and taking time to rest are also key. Remember to have a low threshold for seeking medical help when you are far from home.\n\n"+
            "Q. Where can I find information about travel vaccinations?\n"+
            "A. Travellers should consult a travel medical centre, or their local doctor, at least 6 - 12 weeks before departure, for a check-up and to discuss required and recommended vaccinations for specific regions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assist);

        findViewById(R.id.checklisthd).setVisibility(View.INVISIBLE);
        findViewById(R.id.checklist).setVisibility(View.INVISIBLE);
        findViewById(R.id.healthfaq).setVisibility(View.INVISIBLE);
        findViewById(R.id.healthfaq_hd).setVisibility(View.INVISIBLE);

        Button assistButton = (Button)findViewById(R.id.btnSubmit);
        assistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView checkList = (TextView) findViewById(R.id.checklist);
                checkList.setText(checkListStr);
                TextView healthfaq = (TextView) findViewById(R.id.healthfaq);
                healthfaq.setText(faqStr);
                findViewById(R.id.checklisthd).setVisibility(View.VISIBLE);
                findViewById(R.id.checklist).setVisibility(View.VISIBLE);
                findViewById(R.id.healthfaq).setVisibility(View.VISIBLE);
                findViewById(R.id.healthfaq_hd).setVisibility(View.VISIBLE);
            }
        });


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
    public void goToHome(View view) {
        Intent mainIntent = new Intent(this,MainActivity.class);
        ImageButton mainPlan = (ImageButton) findViewById(R.id.assHme);

    if(Build.VERSION.SDK_INT == 21)   {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, mainPlan, "assHome");
        startActivity(mainIntent,options.toBundle());

    }
    else{

        startActivity(mainIntent);
    }

}
}
