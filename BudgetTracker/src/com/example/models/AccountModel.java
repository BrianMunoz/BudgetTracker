package com.example.models;

import android.os.Parcel;
import android.os.Parcelable;

public class AccountModel implements Parcelable {
	private double currentBalance;
	private String accountName;
	
	/**
	 * The constructor for creating a new model from the new account activity.
	 * @param startingAmount String the starting balance of a new account
	 * @param accountName String the user defined name of the account
	 */
	public AccountModel(double startingAmount, String accountName){
		currentBalance = startingAmount;
		this.accountName = accountName;
	}
	
	public AccountModel(Parcel in)
	{
		String[] data = new String[2];
		in.readStringArray(data);
		accountName = data[0];
		currentBalance = Double.parseDouble(data[1]);
	}	

	public String getAccountName(){
		return accountName;
	}
	
	public void setCurrentBalance(double newAmount){
		currentBalance = newAmount;
	}
	
	public double getCurrentBalance(){
		return currentBalance;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel destination, int flags) {
		destination.writeStringArray(new String[]{this.accountName, Double.toString(this.currentBalance)});
	}
	public static final Parcelable.Creator<AccountModel> CREATOR = new Parcelable.Creator<AccountModel>(){
		public AccountModel createFromParcel(Parcel in){
			return new AccountModel(in);
		}
		
		public AccountModel[] newArray(int size){
			return new AccountModel[size];
		}
	};
}
