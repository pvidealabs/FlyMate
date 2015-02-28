package app.labs.idea.com.flymate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;


public class PlanActivity extends Activity {

    private TextView tvDisplayDate;
    private DatePicker dpResult;
    private Button btnChangeDate;

    private int year;
    private int month;
    private int day;
    private int date;

    static final int DATE_DIALOG_ID = 999;
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MMM-yyyy");
    public static final String DEST_CITY = "DEST_CITY";
    public static final String DEPT_CITY = "DEPT_CITY";
    public static final String SEL_DATE = "DATE";
    public static final String[] monthName = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    public static final String[] weekDay = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        setMenuList();
        //setCurrentDateOnView();
    }

    private void setMenuList() {

        Spinner toSpinner = (Spinner) findViewById(R.id.toSpinner);
        Spinner frmSpinner = (Spinner) findViewById(R.id.frmSpinner);
        List<String> list = new ArrayList<String>();
        list.add("Auckland, New Zealand(AKL)");
        list.add("Chennai, India(MAA)");
        list.add("Ibiza, Spain(IBZ)");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        toSpinner.setAdapter(dataAdapter);
        toSpinner.setPrompt("Choose Departure City");
        frmSpinner.setAdapter(dataAdapter);
        frmSpinner.setPrompt("Choose Destination City");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();





        return super.onOptionsItemSelected(item);
    }

    public void setCurrentDateOnView() {

       /* TextView dateCont = (TextView) findViewById(R.id.dateTxt);
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        date= c.get(Calendar.DAY_OF_MONTH);
        day = c.get(Calendar.DAY_OF_WEEK);
        // set current date into textview
        dateCont.setText(""+date);*/




    }

    public void getDate(View v){
        final TextView dateCont = (TextView) findViewById(R.id.dateCont);
        DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {
                Calendar c = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                date= c.get(Calendar.DAY_OF_MONTH);
                day = c.get(Calendar.DAY_OF_WEEK);
                // set current date into textview
                String dateStr=date+"-"+(month+1)+"-"+year;
                dateCont.setText(dateStr);

            }
        };
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(v.getContext(), datePickerListener,year, month,day);
        dialog.show();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showDetails(View view) {

        HashMap<String,String> codeList = new HashMap<>();
        codeList.put("Auckland, New Zealand(AKL)","AKL");
        codeList.put("Chennai, India(MAA)","MAA");
        codeList.put("Ibiza, Spain(IBZ)","IBZ");

        Spinner toSpinner = (Spinner) findViewById(R.id.toSpinner);
        Spinner frmSpinner = (Spinner) findViewById(R.id.frmSpinner);
        String destCity = codeList.get(toSpinner.getSelectedItem().toString());
        String deptCity = codeList.get(frmSpinner.getSelectedItem().toString());
        String selDate = "18-01-2015";

        if(selDate.equalsIgnoreCase("Choose Date")){
            Toast.makeText(PlanActivity.this,"Please choose a date !!", Toast.LENGTH_LONG).show();
        }else{
            Intent planDets = new Intent(this,PlanCardActivity.class);
            planDets.putExtra(DEPT_CITY,deptCity);
            planDets.putExtra(DEST_CITY,destCity);
            planDets.putExtra(SEL_DATE,selDate);

            ImageButton mainPlan = (ImageButton) findViewById(R.id.hmeBtn);

        if(Build.VERSION.SDK_INT == 21)   {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, mainPlan, "planTrans");
            startActivity(planDets,options.toBundle());

        }
        else{

            startActivity(planDets);
        }

        }




    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void goToHome(View view){
        Intent mainIntent = new Intent(this,MainActivity.class);
        ImageButton mainPlan = (ImageButton) findViewById(R.id.hmeBtn);

     if(Build.VERSION.SDK_INT == 21)   {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, mainPlan, "planTrans");
        startActivity(mainIntent,options.toBundle());

    }
    else{

        startActivity(mainIntent);
    }


}
}
