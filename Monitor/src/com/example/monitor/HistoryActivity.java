package com.example.monitor;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HistoryActivity extends Activity{
	TextView tv_name;
	ListView lv_report;
	userDataBase db;
	List<String> display_data;
	ArrayAdapter<String> aa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		String id = i.getStringExtra("id");
		display_data= new ArrayList<String>();
		
		setContentView(R.layout.activity_displayreport);
		tv_name=(TextView)findViewById(R.id.textViewResultName);
		lv_report=(ListView)findViewById(R.id.listViewResult);
		db= new userDataBase(getApplicationContext(), "", null, 1);
		
		Cursor c = db.getUser(Integer.parseInt(id));
		c.moveToFirst();
		tv_name.setText(c.getString(1));
		
		c= db.getResult(Integer.parseInt(id));
		if(c.getCount()>0){
			c.moveToFirst();
			while(!c.isAfterLast()){
				display_data.add("Date : "+c.getString(2)+" BMP : "+c.getInt(1));
				c.moveToNext();
				aa= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, display_data);
				lv_report.setAdapter(aa);
			}
			
		}else{
			Toast.makeText(getApplicationContext(), "No previous Result found", Toast.LENGTH_LONG).show();
		}
	}
	
	public void back(View v) {
		finish();
		
	}

}
