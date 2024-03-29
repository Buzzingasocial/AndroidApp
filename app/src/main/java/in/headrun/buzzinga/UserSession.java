package in.headrun.buzzinga;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by headrun on 22/7/15.
 */
public class UserSession {

    public static final String TSESSION = "tsession";
    public static final String count_CLUBBEDQUERY = "count_clubbedquery";
    public static final String search_CLUBBEDQUERY = "search_clubbedquery";
    public static final String TIMEZONE = "timezone";
    public static final String SETUP = "setup";
    public static final String FROM_DATE = "fromdate";
    public static final String TO_DATE = "to";
    public static final String LATEST_DATE = "laestdate";
    public static final String TRACK_KEY = "trackkey";
    private static final String PREFER_NAME = "BUZZINGA";
    private static final boolean BUZZ_NOTIFY_SEL = false;

    private static final String TACK_SEARCH_KEY = "";

    public static String BTRACKKEY = "";
    public static String BSources = "";

    public String TAG = UserSession.this.getClass().getSimpleName();
    public SharedPreferences pref;
    public Context _context;
    public Editor editor;
    int PRIVATE_MODE = 0;

    public UserSession(Context context) {
        this._context = context;

        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.commit();
    }

    public String getTSESSION() {
        String sess = pref.getString(TSESSION, "");

        return sess;
    }

    public void setTSESSION(String sessiondata) {

        editor.putString(TSESSION, sessiondata);
        editor.commit();
    }

    public void clearsession() {

        editor.remove(TSESSION);
        editor.commit();

    }

    public String getClubbedquery() {
        String clubbedquery = pref.getString(count_CLUBBEDQUERY, "");

        return clubbedquery;
    }

    public void setClubbedquery(String clubbedquery) {

        editor.putString(count_CLUBBEDQUERY, clubbedquery);
        editor.commit();
        String query = pref.getString(count_CLUBBEDQUERY, "");


    }

    public String get_search_Clubbedquery() {
        String clubbedquery = pref.getString(search_CLUBBEDQUERY, "");

        return clubbedquery;
    }

    public void set_search_Clubbedquery(String clubbedquery) {

        editor.putString(search_CLUBBEDQUERY, clubbedquery);
        editor.commit();
        String query = pref.getString(search_CLUBBEDQUERY, "");


    }

    public boolean isBUZZ_NOTIFY_SEL() {
        Log.i(TAG, "BUZZ_NOTIFY_SEL" + pref.getBoolean("BUZZ_NOTIFY_SEL", false));
        return pref.getBoolean("BUZZ_NOTIFY_SEL", false);
    }

    public void setBUZZ_NOTIFY_SEL(boolean notifysel) {

        editor.putBoolean("BUZZ_NOTIFY_SEL", notifysel);
        editor.commit();
    }


    public String getTIMEZONE() {
        String timezone = pref.getString(TIMEZONE, "null");

        return timezone;
    }

    public void setTIMEZONE(String timezone) {

        editor.putString(TIMEZONE, timezone);
        editor.commit();
        String time_zone = pref.getString(TIMEZONE, "");
    }

    public String getSETUP() {

        String setup = pref.getString(SETUP, "null");
        Log.i(TAG, "setup is" + setup);
        return setup;
    }

    public void setSETUP(String setup) {

        editor.putString(SETUP, setup);
        editor.commit();
        String set_up = pref.getString(SETUP, "");

    }


    public String getLatestDate() {
        String getLastestDate = pref.getString(LATEST_DATE, "0");

        return getLastestDate;
    }

    public void setLatestDate(String latestDate) {

        editor.putString(LATEST_DATE, latestDate);
        editor.commit();
        String query = pref.getString(LATEST_DATE, "");


    }

    public String getTrackKey() {
        String getTrackKey = pref.getString(TRACK_KEY, "");

        return getTrackKey;
    }

    public void setTrackKey(String trackKey) {

        editor.putString(TRACK_KEY, trackKey);
        editor.commit();
        String query = pref.getString(TRACK_KEY, "");
    }

    public void setTACK_SEARCH_KEY(String search_key) {
        editor.putString(TACK_SEARCH_KEY, search_key);
        editor.commit();
    }

    public String gettTACK_SEARCH_KEY() {
        return pref.getString(TACK_SEARCH_KEY, "");
    }

    public String getFROM_DATE() {
        return pref.getString(FROM_DATE, "");

    }

    public void setFROM_DATE(String fromdate) {
        editor.putString(FROM_DATE, fromdate);
        editor.commit();
    }

    public String getTO_DATE() {
        return pref.getString(TO_DATE, "");
    }

    public void setTO_DATE(String todate) {
        editor.putString(TO_DATE, todate);
        editor.commit();
    }

    public void setBTRACKKEY(String btrackkey, ArrayList<String> list) {
        editor.putString(BTRACKKEY, list.toString());
        editor.commit();
    }

    public String getBTRACKKEY() {
        return pref.getString(BTRACKKEY, "");
    }

    public void seetBSources(String Bsource, ArrayList<String> list) {
        editor.putString(BSources, list.toString());
    }
}

