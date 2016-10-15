package com.example.monitor;



import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity{
	String name,id;
	TextView tv_name,tv_result;
	int[] mic,cam;
	int AGE=0;
	userDataBase db;
	float res,avgcam;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		tv_name=(TextView)findViewById(R.id.textViewRname);
		tv_result=(TextView)findViewById(R.id.textViewResultVals);
		
		db= new userDataBase(getApplicationContext(), "", null, 1);
		
		Intent i = getIntent();
		tv_name.setText(i.getStringExtra("name"));
		mic=i.getIntArrayExtra("mic");
		cam=i.getIntArrayExtra("cam");
		id=i.getStringExtra("id");
		name=i.getStringExtra("name");
		
		Cursor g = db.getUser(Integer.valueOf(id));
		
		g.moveToFirst();
		
		
		
		AGE=g.getInt(4);
		
		int camA=0,micA=0;
		
		for(int j=0 ;j<=59; j++){
			camA+=cam[j];
			micA+=mic[j];
		}
		
	    avgcam=camA/60;
		float avgmic=micA/60;
		res= (avgcam+avgmic/2);
		tv_result.setText("Your heart rate is " + avgcam);
	}
	
	
	
	
	public void addDB(View v){
		if((int)avgcam>=80){
			String SENT = "SMS_SENT";
			String DELIVERED = "SMS_DELIVERED";

			PendingIntent sentPI = PendingIntent.getBroadcast(getApplicationContext(), 0,
					new Intent(SENT), 0);

			PendingIntent deliveredPI = PendingIntent.getBroadcast(getApplicationContext(), 0,
					new Intent(DELIVERED), 0);
			
			Cursor c = db.getUser(Integer.valueOf(id));
			
			c.moveToFirst();
			
			
			
			String phoneNumber=c.getString(5);
			String message="user : "+ name + " has heart rate  value : "+ String.valueOf(avgcam);
			SmsManager sms = SmsManager.getDefault();
			sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
			Toast.makeText(getApplicationContext(), message + phoneNumber, Toast.LENGTH_LONG)
					.show();
		}

		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String formattedDate = df.format(c.getTime());
		db.addResult(Integer.valueOf(id), (int)res, formattedDate);
		finish();
	}
	public void back(View v) {
		finish();
		
	}
}
