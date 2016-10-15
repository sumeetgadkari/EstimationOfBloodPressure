package com.example.monitor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddUserActivity extends Activity{
	
	EditText name,height,weight,number,age;
	userDataBase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addnew);
		
		name=(EditText)findViewById(R.id.editTextUSERNAME);
		height=(EditText)findViewById(R.id.editTextHEIGHT);
		weight=(EditText)findViewById(R.id.editTextWEIGHT);
		age=(EditText)findViewById(R.id.editTextAGE);
		number=(EditText)findViewById(R.id.editTextCONTNUMBER);
		
		db= new userDataBase(getApplicationContext(), "", null, 1);
		
		

		
	}
	
	public void back(View v){
		finish();
	}
	public void addNEW(View v) {
		
		String tNAME=name.getText().toString();
		String tNUMBER=number.getText().toString();
		int tHEIGHT=Integer.parseInt(height.getText().toString());
		int tWEIGHT= Integer.parseInt(weight.getText().toString());
		int tAGE= Integer.parseInt(age.getText().toString());
		if(tNAME.length()>0 )
		
		db.addUser(tNAME, tWEIGHT, tHEIGHT,tAGE,tNUMBER);
		
		finish();
		
		
		
	}
}
