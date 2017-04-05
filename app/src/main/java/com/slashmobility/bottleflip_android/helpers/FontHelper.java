package com.slashmobility.bottleflip_android.helpers;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.slashmobility.bottleflip_android.utils.FontCache;


/**

 * @author Rony Diaz
 * @version %I, %G
 * @since 1.0
 */
public class FontHelper {

	public static void setCustomFont(TextView textview, Context context, String name) {
		if (textview != null && context != null && name != null) {
            Typeface tf = FontCache.get(name, context);
            if (tf != null) {
                textview.setTypeface(tf);
            }
        }
	}

}