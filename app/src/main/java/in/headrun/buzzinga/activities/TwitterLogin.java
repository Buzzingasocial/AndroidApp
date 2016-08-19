package in.headrun.buzzinga.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.headrun.buzzinga.R;
import in.headrun.buzzinga.UserSession;
import in.headrun.buzzinga.config.ServerConfig;

import in.headrun.buzzinga.utils.Utils;

/**
 * Created by headrun on 21/7/15.
 */
public class TwitterLogin extends Activity {

    String TAG = TwitterLogin.this.getClass().getSimpleName();
    TwitterSession session;

    @Bind(R.id.twitter_login_button1)
    ImageView loginButton;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.twitter_btn)
    View twitter_btn;
    @Bind(R.id.twitter_auth_lay)
    View twitter_auth_lay;
    @Bind(R.id.webview)
    WebView webview;

    private TwitterLoginButton TloginButton;
    private Button btnuserdetails;
    public String stoken, token, userid, username;
    Utils utils;

    String cookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitterlogin);

        ButterKnife.bind(this);
        utils = new Utils(this);

        twitter_btn.setVisibility(View.VISIBLE);
        twitter_auth_lay.setVisibility(View.GONE);
        btnuserdetails = (Button) findViewById(R.id.btnuserdetails);
        webview.setWebViewClient(new MyBrowser());
        webview.getSettings().setLoadsImagesAutomatically(true);
        //webview.getSettings().setJavaScriptEnabled(true);
        //webview.getSettings().setDomStorageEnabled(true);
        //webview.getSettings().getAllowContentAccess();
        webview.getSettings().setLoadWithOverviewMode(true);
        // webview.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        //webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        Log.d(TAG, "loading urls is" + ServerConfig.SERVER_ENDPOINT + ServerConfig.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (utils.isNetwrokConnection()) {
                    twitter_btn.setVisibility(View.GONE);
                    twitter_auth_lay.setVisibility(View.VISIBLE);

                    webview.loadUrl(ServerConfig.SERVER_ENDPOINT + ServerConfig.login);

                } else {
                    Toast.makeText(getApplication(), "Network error", Toast.LENGTH_LONG).show();
                }
            }
        });

        TloginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        TloginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()

                session = result.data;
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                stoken = session.getAuthToken().secret;
                token = session.getAuthToken().token;
                userid = String.valueOf(session.getUserId());
                username = session.getUserName();

                Log.i(TAG, "secreat key " + session.getAuthToken().secret +
                        "\ntoken " + session.getAuthToken().token +
                        "\nuser id " + session.getUserId() +
                        "\nuser name " + session.getUserName());

                twitter_btn.setVisibility(View.GONE);
                twitter_auth_lay.setVisibility(View.GONE);
                btnuserdetails.setVisibility(View.VISIBLE);

                getTwitterdata();

            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });


        btnuserdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Twitter.getApiClient(session).getAccountService()
                        .verifyCredentials(true, false, new Callback<User>() {

                            @Override
                            public void success(Result<User> userResult) {

                                User user = userResult.data;
                                //twitterImage = user.profileImageUrl;

                                Log.i(TAG, "user email" + user.email+
                                            "user id string"+user.idStr+
                                                "user name"+user.name
                                );

                            }

                            @Override
                            public void failure(TwitterException e) {

                            }

                        });
                        */

                /*
                TwitterAuthClient authClient = new TwitterAuthClient();
                authClient.requestEmail(session, new Callback<String>() {
                    @Override
                    public void success(Result<String> result) {
                        // Do something with the result, which provides the email address

                   Log.i(TAG,"email address is"+result.data.toString());
                   Log.i(TAG,"email address is"+result.response.toString());
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        // Do something on failure
                    }
                });
                */

            }
        });


    }

    public void getTwitterdata() {
        Twitter.getApiClient(session).getAccountService()
                .verifyCredentials(true, false, new Callback<User>() {

                    @Override
                    public void success(Result<User> userResult) {

                        User user = userResult.data;
                        //twitterImage = user.profileImageUrl;

                        Log.i(TAG, "user Screen name" + userResult.data.screenName +
                                "\nuser email" + userResult.data.email +
                                "\nuser name" + userResult.data.name
                        );

                    }

                    @Override
                    public void failure(TwitterException e) {

                    }

                });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        Log.i(TAG, "data is" + data.getDataString());
        TloginButton.onActivityResult(requestCode, resultCode, data);
    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Log.i(TAG, "url loading");
            view.loadUrl(url);

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressbar.setProgress(100);
            progressbar.setVisibility(View.GONE);
            Log.d(TAG, "TITLE is" + view.getTitle() + "\nurl is" + view.getOriginalUrl());

            if (view.getOriginalUrl() != null) {

               /* if (view.getOriginalUrl().equals("http://beta.buzzinga.com/link_socialmedia/")) {
                    cookie = CookieManager.getInstance().getCookie(url);
                    new UserSession(TwitterLogin.this).clearsession(new UserSession(TwitterLogin.this).TSESSION);

                    if (cookie.contains("csrftoken")) {
                        Log.i(TAG, "csrf token has exists " + cookie.getBytes().toString());
                    } else {
                        Log.i(TAG, "csrf token has not exists ");
                    }

                    // if (cookie.contains("sessionid") && cookie.contains("csrftoken"))
                    new UserSession(TwitterLogin.this).setTSESSION(cookie.toString());

                    Log.i(TAG, "url cookie is" + cookie.toString());

                    if (new UserSession(TwitterLogin.this).getTSESSION().length() > 0)
                        startActivity(new Intent(TwitterLogin.this, TrackKeyWord.class));

                }*/

                cookie = CookieManager.getInstance().getCookie(url);

                if (cookie != null) {

                    String[] cookie_extract = cookie.split(";");
                    for (String cookivalue : cookie_extract) {
                        Log.i(TAG, "cookie value is" + cookivalue);
                    }

                    for (String cookivalue : cookie_extract) {

                        if (cookivalue.contains("sessionid")) {
                            String[] temp1 = cookivalue.split("=");
                            Log.i(TAG, "session id is" + temp1[1]);
                            new UserSession(TwitterLogin.this).setTSESSION(temp1[1]);
                        }
                    }

                    if (new UserSession(TwitterLogin.this).getTSESSION().length() > 0)
                        startActivity(new Intent(TwitterLogin.this, TrackKeyWord.class));
                }
            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressbar.setProgress(0);
            progressbar.setVisibility(View.VISIBLE);
        }
    }


    public void postdetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ServerConfig.SERVER_ENDPOINT + ServerConfig.logout,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG, "string response is" + response);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "string error response is" + error);

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("clubbed_query", stoken);
                params.put("setup", token);
                params.put("setup", userid);
                params.put("setup", username);

                return params;
            }

        };

    }
}