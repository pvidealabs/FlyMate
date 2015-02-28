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


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void goToPlanActivity(View view){

        Intent planIntent = new Intent(this,PlanActivity.class);
        Button mainPlan = (Button) findViewById(R.id.main_plan);

    if (Build.VERSION.SDK_INT == 21) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, mainPlan, "planTrans");
        startActivity(planIntent,options.toBundle());

        overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_fade_out);

    }
    else{

        startActivity(planIntent);
    }


}
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void goToAssistActivity(View view){

        Intent assistIntent = new Intent(this,AssistActivity.class);
        Button mainPlan = (Button) findViewById(R.id.assBtn);

     if (Build.VERSION.SDK_INT == 21) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, mainPlan, "assHome");
        startActivity(assistIntent,options.toBundle());

        overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_fade_out);

    }
    else{

        startActivity(assistIntent);
    }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void goToTravelActivity(View view){


        Intent travelIntent = new Intent(this,MapsActivity.class);
        Button mainPlan = (Button) findViewById(R.id.trvl_main);

        if (Build.VERSION.SDK_INT == 21) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, mainPlan, "mapHome");
            startActivity(travelIntent,options.toBundle());

            overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_fade_out);
        }
            else{

            startActivity(travelIntent);
            }


    }


}
