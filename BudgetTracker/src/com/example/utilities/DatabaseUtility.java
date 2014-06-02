package com.example.utilities;

import com.example.utilities.BudgetTrackerContract.AccountEntry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseUtility extends SQLiteOpenHelper {

	
	
	public DatabaseUtility(Context context){
		super (context, BudgetTrackerContract.DATABASE_NAME, null, BudgetTrackerContract.DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){		
		db.execSQL(AccountEntry.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

		db.execSQL(AccountEntry.DELETE_TABLE);
		onCreate(db);
	}
	

}
