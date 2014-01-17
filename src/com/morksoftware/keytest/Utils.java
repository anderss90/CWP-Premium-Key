package com.morksoftware.keytest;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class Utils {
	public static boolean hasWallpaper(Context context) {
		
		try {
			context.getPackageManager().getPackageInfo("com.morksoftware.plwplus", PackageManager.GET_META_DATA);
		}
		catch(NameNotFoundException e){
			return false;
		}
		
		return true;
	}
}
