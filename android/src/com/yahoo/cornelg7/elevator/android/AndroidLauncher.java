package com.yahoo.cornelg7.elevator.android;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.yahoo.cornelg7.elevator.Elevator;
import utils.Const;

public class AndroidLauncher extends AndroidApplication {
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);

		Const.SCREEN_WIDTH = size.x;
		Const.SCREEN_HEIGHT = size.y;
		Const.PRO_WIDTH = Const.SCREEN_WIDTH * 0.5625f;
		Const.PRO_HEIGHT = Const.PRO_WIDTH * 0.1388f;
		Const.crystalSize = Const.PRO_HEIGHT;
		Const.STANDARD_MARGIN.set(0.078f * Const.SCREEN_WIDTH, 0.19f * Const.SCREEN_HEIGHT);

		initialize(new Elevator(), config);
	}
}
