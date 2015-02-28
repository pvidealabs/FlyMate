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
import android.widget.ImageButton;
import android.widget.TextView;


public class PlanTabActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_tab);

        Intent intent = getIntent();
        String destCity = intent.getStringExtra(PlanActivity.DEST_CITY);
        String deptCity = intent.getStringExtra(PlanActivity.DEPT_CITY);
        String selDate = intent.getStringExtra(PlanActivity.SEL_DATE);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plan_tab, menu);
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
    public void goToHome(View view){

        Intent mainIntent = new Intent(this,PlanActivity.class);
        ImageButton mainPlan = (ImageButton) findViewById(R.id.hmePlnBtn);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, mainPlan, "planTrans");

        startActivity(mainIntent,options.toBundle());

    }
}
