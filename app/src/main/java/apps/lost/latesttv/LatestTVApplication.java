package apps.lost.latesttv;

import android.app.Application;

import apps.lost.latesttv.shows.selection.PresenterModule;

/**
 * Created by luke
 */
public class LatestTVApplication extends Application {

    private PresenterModule mPresenterModule;

    @Override
    public void onCreate() {
        super.onCreate();
        mPresenterModule = new PresenterModule();
    }

    public PresenterModule getPresenterModule() {
        return mPresenterModule;
    }
}
