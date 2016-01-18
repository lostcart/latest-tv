package apps.lost.latesttv.shows.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import apps.lost.latesttv.shows.Show;
import de.greenrobot.event.EventBus;

/**
 * Handles displaying the details for a particular show
 *
 * @author luke
 */
public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ShowFragment showFragment = (ShowFragment) getSupportFragmentManager().findFragmentByTag("fragment");

        if (showFragment == null) {
            showFragment = new ShowFragment();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, showFragment, "fragment")
                    .commit();
            showFragment.setShow(EventBus.getDefault().removeStickyEvent(Show.class));
        }
    }
}
