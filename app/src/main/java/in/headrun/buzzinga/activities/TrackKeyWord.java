package in.headrun.buzzinga.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.headrun.buzzinga.R;
import in.headrun.buzzinga.config.Config;
import in.headrun.buzzinga.config.Constants;
import in.headrun.buzzinga.doto.Utils;

/**
 * Created by headrun on 4/9/15.
 */
public class TrackKeyWord extends Activity implements View.OnClickListener {

    public String TAG = TrackKeyWord.this.getClass().getSimpleName();

    @Bind(R.id.keyword)
    EditText keyword;
    @Bind(R.id.trackbtn)
    Button trackbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trackkeyword);
        ButterKnife.bind(this);

        trackbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.trackbtn:
                String[] track_word = {keyword.getText().toString().toString()};
                if (Config.TRACKKEYWORD)
                    Log.i(TAG, "track key word is" + track_word[0]);
                if (track_word[0].length() > 0) {
                    new Utils(this).clear_all_data();
                    Constants.BTRACKKEY.add(track_word[0].toString());
                    Utils.add_query_data();
                    Constants.listdetails.clear();
                    Intent intent = new Intent(getApplication(), HomeScreen.class);
                    intent.putExtra(Constants.Intent_OPERATION, Constants.Intent_TRACK);
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(this, "Enter your brand", Toast.LENGTH_LONG).show();

                break;
        }
    }
}
