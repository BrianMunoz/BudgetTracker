package com.example.utilities;

import android.provider.BaseColumns;

public final class BudgetTrackerContract {
	public BudgetTrackerContract() {}
	
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "BudgetTracker.db";
	
	
	
	public static abstract class AccountEntry implements BaseColumns{
		public static final String TABLE_NAME = "Accounts";
		public static final String COLUMN_ACCOUNT_NAME = "accountname";
		public static final String COLUMN_BALANCE = "balance";
		
		public static final String CREATE_TABLE ="CREATE TABLE " + AccountEntry.TABLE_NAME + " ("
				+ AccountEntry.COLUMN_ACCOUNT_NAME + " TEXT PRIMARY KEY, " 
				+ AccountEntry.COLUMN_BALANCE+" REAL NOT NULL )";
		
		public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + AccountEntry.TABLE_NAME; 
	}
}
