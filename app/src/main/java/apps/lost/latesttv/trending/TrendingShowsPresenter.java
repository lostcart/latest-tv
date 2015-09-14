package apps.lost.latesttv.trending;

import java.util.List;

import apps.lost.latesttv.shows.Show;
import apps.lost.latesttv.trending.service.TrendingShowsManager;

/**
 * Handles firing off events to the show manager, manipulating the data returned if needed
 * and returns data back to the view
 * view
 * <p/>
 * Created by luke
 */
public class TrendingShowsPresenter implements TrendingShowsManager.TrendingShowsCallback {

    private TrendingShowsManager mTrendingShowsManager;

    private View mView;

    public TrendingShowsPresenter(View view, TrendingShowsManager trendingShowsManager) {
        mView = view;
        mTrendingShowsManager = trendingShowsManager;
    }

    public void onViewAttached() {
        mTrendingShowsManager.getShows(this);
    }

    @Override
    public void gotShows(List<Show> shows) {
        mView.showShows(shows);
    }

    @Override
    public void failed() {
        mView.showError();
    }

    /**
     * Interface for declaring all the things that the view
     * should be able to do, that we want to control from here..
     */
    public interface View {
        void showShows(List<Show> shows);

        void showError();
    }
}
