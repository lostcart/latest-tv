package apps.lost.latesttv;

import android.app.Activity;
import android.os.Bundle;

/**
 * Handles displaying the trending shows fragment
 *
 * @author luke
 */
public class LatestTVActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.latest_tv);
    }
}
