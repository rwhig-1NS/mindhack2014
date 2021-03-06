package com.example.mindhack2014;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class User extends SQLiteOpenHelper {

	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "lobster_muffins";
	
	private static final String KEY_NAME = "name";
	private static final String KEY_TYPE = "type";
	private static final String KEY_VALUE = "value";

	// Contacts table name
	private static final String TABLE = "user_items";

	public User(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/*
	 * Ex:
	 * 
	 * Item newItem = new Item();
	 * newItem.setName("Super Awesome Power Up");
	 * newItem.setType("item");
	 * newItem.setValue(2);
	 * 
	 * ItemDao itemDaoInst = new ItemDao(...);
	 * itemDaoInst.create(newItem);
	 */
	public void addItem(Item item) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, item.getName());
		values.put(KEY_TYPE, item.getType());
		values.put(KEY_VALUE, item.getValue());
		
		// Inserting Row
		db.insert(TABLE, null, values);
		db.close(); // Closing database connection
	}
	
	public Item findByName(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE, new String[] { KEY_NAME, KEY_TYPE, KEY_VALUE }, 
				KEY_NAME + "=?", new String[] { name }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Item item = new Item();
		item.setName(cursor.getString(0));
		item.setType(cursor.getString(1));
		item.setValue(cursor.getInt(2));
		
		// return contact
		return item;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_TABLE = "CREATE TABLE " + TABLE + "("
				+ KEY_NAME + " STRING PRIMARY KEY," + KEY_TYPE + " TEXT,"
				+ KEY_VALUE + " INTEGER" + ")";
		db.execSQL(CREATE_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);

		// Create tables again
		onCreate(db);
	}
	
	
	
	// Getting All Contacts
	public List<Item> getAllItems() {
		List<Item> itemList = new ArrayList<Item>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Item item = new Item();
				item.setName(cursor.getString(0));
				item.setType(cursor.getString(1));
				item.setValue(Integer.parseInt(cursor.getString(2)));
				// Adding contact to list
				itemList.add(item);
			} while (cursor.moveToNext());
		}

		// return contact list
		return itemList;
	}
	
}
