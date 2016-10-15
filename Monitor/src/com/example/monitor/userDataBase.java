package com.example.monitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class userDataBase extends SQLiteOpenHelper{
	
	private static String DATABASE_NAME="HBM";
	private static int DATABASE_VER=1;
	

	public userDataBase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VER);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table users (id integer primary key autoincrement, name text, weight integer, height integer,age integer,number text )");
		db.execSQL("create table results (id integer, value number, date text )");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("Drop table users");
		db.execSQL("Drop table results");
		onCreate(db);
		
	}
	
	public void addUser(String name, int weight, int height,int age, String number){
		
		
		number="+91"+number;
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("weight", weight);
		cv.put("height", height);
		cv.put("age", age);
		cv.put("number", number);
		SQLiteDatabase db = getWritableDatabase();
		db.insert("users", null, cv);
		db.close();
		cv.clear();
		
	}
	
	public Cursor getUsers(){
		SQLiteDatabase db = getReadableDatabase();
	
		return db.rawQuery("select * from users",null);
		
	}
	public Cursor getUser(int id){
		SQLiteDatabase db = getReadableDatabase();
	
		return db.rawQuery("select * from users where id=?",new String[]{String.valueOf(id) });
		
	}
	

	public void addResult( int id, int value, String date){
		ContentValues cv = new ContentValues();
	
		cv.put("id", id);
		cv.put("value", value);
		cv.put("date", date);
		SQLiteDatabase db = getWritableDatabase();
		db.insert("results", null, cv);
		db.close();
		cv.clear();
		
	}
	public Cursor getResult(int id){
		SQLiteDatabase db = getReadableDatabase();
	
		return db.rawQuery("select * from results where id=?",new String[]{String.valueOf(id) });
		
	}
	
	
}
