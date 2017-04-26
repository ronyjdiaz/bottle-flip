package com.slashmobility.bottleflip_android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import net.protyposis.android.mediaplayer.MediaSource;
import net.protyposis.android.mediaplayer.UriSource;
import net.protyposis.android.mediaplayer.dash.AdaptationLogic;
import net.protyposis.android.mediaplayer.dash.DashSource;
import net.protyposis.android.mediaplayer.dash.SimpleRateBasedAdaptationLogic;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;


/**
 * Created by Rony
 */

public class Utils {

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }


    public static int pxToDp(int px, Context context){

        int pixel = Math.round(px * (context.getResources().getDisplayMetrics().density));
        Log.e("pxtodp"+px, pixel + "");
        return pixel;
    }

    public static void changeColorDrawable(View v, Context context, int color){
        GradientDrawable gd = (GradientDrawable) v.getBackground().mutate();
        gd.setColor(ContextCompat.getColor(context, color));
    }

    public static void changeStrokeColorDrawable(View v, Context context, int stroke, int color){
        GradientDrawable gd = (GradientDrawable) v.getBackground().mutate();
        gd.setStroke(stroke, ContextCompat.getColor(context, color));
    }

    public static boolean isPair(int numero) {
        if(numero % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void openExternalLink(Context context, String link){
        Intent intent = null;
        intent = new Intent(intent.ACTION_VIEW, Uri.parse(link));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            //TECLADO NO CREADO
        }
    }

    public static boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }


    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    public static SpannableString underlinetext(String text){
        SpannableString content = new SpannableString(text);
        content.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        return content;
    }

    public static Calendar String2Calendar(String date){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+04:00"));
        Date d = new Date( Long.parseLong(date) *1000);
        calendar.setTime(d);
        return calendar;
    }
    public static Calendar String2Calendar2(String date){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+04:00"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date d = null;
        try {
            d = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(d);
        return calendar;
    }

    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }

    public static String getDayForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getWeekdays();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }

    public static String differenceDate(Date startDate, Date endDate){
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;
        if(elapsedDays>0){
            return String.valueOf(elapsedDays) + " d";
        }else
        if(elapsedHours>0){
            return String.valueOf(elapsedHours) + " h";
        }
        else
        if(elapsedMinutes>0)
        {
            return String.valueOf(elapsedMinutes) + " m";
        }
        else
            return String.valueOf(elapsedSeconds) + " s";





    }


    public static String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 30, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public  static String  uri_to_imageString(Uri uri)
    {
        //Uri hacked_uri = Uri.parse("file://" +  uri.getPath());
        Bitmap bm = BitmapFactory.decodeFile(uri.getPath());
        return getStringImage(bm);


    }
    public static View getToolbarLogoIcon(Toolbar toolbar){
        //check if contentDescription previously was set
        boolean hadContentDescription = android.text.TextUtils.isEmpty(toolbar.getLogoDescription());
        String contentDescription = String.valueOf(!hadContentDescription ? toolbar.getLogoDescription() : "logoContentDescription");
        toolbar.setLogoDescription(contentDescription);
        ArrayList<View> potentialViews = new ArrayList<View>();
        //find the view based on it's content description, set programatically or with android:contentDescription
        toolbar.findViewsWithText(potentialViews,contentDescription, View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        //Nav icon is always instantiated at this point because calling setLogoDescription ensures its existence
        View logoIcon = null;
        if(potentialViews.size() > 0){
            logoIcon = potentialViews.get(0);
        }
        //Clear content description if not previously present
        if(hadContentDescription)
            toolbar.setLogoDescription(null);
        return logoIcon;
    }

    private static final String TAG = Utils.class.getSimpleName();

    public static MediaSource uriToMediaSource(Context context, Uri uri) {
        MediaSource source = null;

        // A DASH source is either detected if the given URL has an .mpd extension or if the DASH
        // pseudo protocol has been prepended.
        if(uri.toString().endsWith(".mpd") || uri.toString().startsWith("dash://")) {
            AdaptationLogic adaptationLogic;

            // Strip dash:// pseudo protocol
            if(uri.toString().startsWith("dash://")) {
                uri = Uri.parse(uri.toString().substring(7));
            }

            //adaptationLogic = new ConstantPropertyBasedLogic(ConstantPropertyBasedLogic.Mode.HIGHEST_BITRATE);
            adaptationLogic = new SimpleRateBasedAdaptationLogic();

            source = new DashSource(context, uri, adaptationLogic);
        } else {
            source = new UriSource(context, uri);
        }
        return source;
    }

    public static void uriToMediaSourceAsync(final Context context, Uri uri, MediaSourceAsyncCallbackHandler callback) {
        LoadMediaSourceAsyncTask loadingTask = new LoadMediaSourceAsyncTask(context, callback);

        try {
            loadingTask.execute(uri).get();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public static void setActionBarSubtitleEllipsizeMiddle(Activity activity) {
        // http://blog.wu-man.com/2011/12/actionbar-api-provided-by-google-on.html
        int subtitleId = activity.getResources().getIdentifier("action_bar_subtitle", "id", "android");
        TextView subtitleView = (TextView) activity.findViewById(subtitleId);
        subtitleView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
    }

    public static boolean saveBitmapToFile(Bitmap bmp, File file) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bmp.compress(Bitmap.CompressFormat.PNG, 90, bos);
            bos.close();
            return true;
        } catch (IOException e) {
            Log.e(TAG, "failed to save frame", e);
        }
        return false;
    }

    private static class LoadMediaSourceAsyncTask extends AsyncTask<Uri, Void, MediaSource> {

        private Context mContext;
        private MediaSourceAsyncCallbackHandler mCallbackHandler;
        private MediaSource mMediaSource;
        private Exception mException;

        public LoadMediaSourceAsyncTask(Context context, MediaSourceAsyncCallbackHandler callbackHandler) {
            mContext = context;
            mCallbackHandler = callbackHandler;
        }

        @Override
        protected MediaSource doInBackground(Uri... params) {
            try {
                mMediaSource = Utils.uriToMediaSource(mContext, params[0]);
                return mMediaSource;
            } catch (Exception e) {
                mException = e;
                return null;
            }
        }

        @Override
        protected void onPostExecute(MediaSource mediaSource) {
            if(mException != null) {
                mCallbackHandler.onException(mException);
            } else {
                mCallbackHandler.onMediaSourceLoaded(mMediaSource);
            }
        }
    }

    public static interface MediaSourceAsyncCallbackHandler {
        void onMediaSourceLoaded(MediaSource mediaSource);
        void onException(Exception e);
    }

    /**
     * Iterates a hierarchy of exceptions and combines their messages. Useful for compact
     * error representation to the user during debugging/development.
     */
    public static String getExceptionMessageHistory(Throwable e) {
        String messages = "";

        String message = e.getMessage();
        if(message != null) {
            messages += message;
        }
        while((e = e.getCause()) != null) {
            message = e.getMessage();
            if(message != null) {
                messages += " <- " + message;
            }
        }

        return messages;
    }


}
