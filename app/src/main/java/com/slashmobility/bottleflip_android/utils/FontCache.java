package com.slashmobility.bottleflip_android.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by ivanquijada on 22/11/16.
 */

public class FontCache {
	
	private static Hashtable<String, Typeface> fontCache = new Hashtable<String, Typeface>();

	public static Typeface get(String name, Context context) {
		if (name != null && context != null) {
            Typeface tf = fontCache.get(name);

            if (tf == null) {
                try {
                    tf = Typeface.createFromAsset(context.getAssets(), name);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                fontCache.put(name, tf);
            }
            return tf;
        }
		return null;
	}
	
}