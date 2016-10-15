package com.example.monitor;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {

	List<String> id, name;
	userDataBase db;
	ArrayAdapter<String> aa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db = new userDataBase(getApplicationContext(), "", null, 1);
		id= new ArrayList<String>();
		name = new ArrayList<String>();
	}

	public void addUser(View v) {
		Intent i = new Intent(getApplicationContext(), AddUserActivity.class);
		startActivity(i);
	}

	public void help(View v) {
		Intent i = new Intent(getApplicationContext(), HelpActivity.class);
		startActivity(i);
	}

	public void exit(View v) {
		finish();
	}

	public void viewHistory(View v) {
		Cursor c = db.getUsers();

		if (c.getCount() > 0) {
			id.clear();
			name.clear();
			c.moveToFirst();
			while (!c.isAfterLast()) {
				id.add(c.getInt(0) + "");
				name.add(c.getString(1));
				c.moveToNext();
			}
			aa = new ArrayAdapter<String>(getApplicationContext(),
					android.R.layout.simple_spinner_dropdown_item, name);
			aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
			final Spinner sp = new Spinner(MainActivity.this);
			sp.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			sp.setAdapter(aa);
			
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		    builder.setView(sp);
		    builder.setTitle("Select User from list");
		    builder.setPositiveButton("view history", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					String selected_id=id.get((int)sp.getSelectedItemId());
					Intent i = new Intent(getApplicationContext(), HistoryActivity.class);
					i.putExtra("id", selected_id);
					startActivity(i);
				}
			});
		    builder.setNegativeButton("Cancel", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
		    builder.create().show();

		} else {
			Toast.makeText(getApplicationContext(), "No user regestred",
					Toast.LENGTH_LONG).show();
		}

	}
	
	public void startM(View v) {
		Cursor c = db.getUsers();

		if (c.getCount() > 0) {
			id.clear();
			name.clear();
			c.moveToFirst();
			while (!c.isAfterLast()) {
				id.add(c.getInt(0) + "");
				name.add(c.getString(1));
				c.moveToNext();
			}
			aa = new ArrayAdapter<String>(getApplicationContext(),
					android.R.layout.simple_spinner_dropdown_item, name);
			aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
			final Spinner sp = new Spinner(MainActivity.this);
			sp.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			sp.setAdapter(aa);
			
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		    builder.setView(sp);
		    builder.setTitle("Select User from list");
		    builder.setPositiveButton("Start Monitoring", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					String selected_id=id.get((int)sp.getSelectedItemId());
					String selected_name=name.get((int)sp.getSelectedItemId());
					Intent i = new Intent(getApplicationContext(), HeartRateMonitor.class);
					i.putExtra("id", selected_id);
					i.putExtra("name", selected_name);
					startActivity(i);
				}
			});
		    builder.setNegativeButton("Cancel", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
		    builder.create().show();

		} else {
			Toast.makeText(getApplicationContext(), "No user regestred",
					Toast.LENGTH_LONG).show();
		}

		
		
	}
}
