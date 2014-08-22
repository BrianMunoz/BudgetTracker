package com.example.utilities;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
public class SwipeDetectorUtility implements View.OnTouchListener{
	
	public static enum SwipeDirection{
		LeftToRight,
		RightToLeft,
		None
	}
	
	private static final int MINIMUM_HORIZONTAL_DISTANCE = 30;
	
	private float firstX, lastX;
	
	
	private SwipeDirection lastSwipeDirection = SwipeDirection.None;
	
	
	public boolean swipeDetected (){
		return lastSwipeDirection != SwipeDirection.None;
	}
	
	public SwipeDirection getSwipeDirection(){
		return lastSwipeDirection;
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent moveEvent) {
		switch (moveEvent.getAction()){
		case MotionEvent.ACTION_DOWN:
			{
				firstX = moveEvent.getX();
				lastSwipeDirection = SwipeDirection.None;
				return false;
			}
		case MotionEvent.ACTION_MOVE:
			{
				firstX = moveEvent.getX();
				float deltaX = firstX - lastX;
				if (Math.abs(deltaX) > MINIMUM_HORIZONTAL_DISTANCE){
					lastSwipeDirection =(deltaX < 0) ? SwipeDirection.LeftToRight : SwipeDirection.RightToLeft ;
					
				}
				return true;
			}
		}
		return false;
	}

}
