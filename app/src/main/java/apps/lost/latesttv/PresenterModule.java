package apps.lost.latesttv;

import apps.lost.latesttv.trending.TrendingShowsPresenter;
import apps.lost.latesttv.trending.service.TrendingShowsManager;

/**
 * Created by luke
 */
public class PresenterModule {

    public TrendingShowsPresenter getTrendingShowsPresenter(TrendingShowsPresenter.View view) {
        return new TrendingShowsPresenter(view, new TrendingShowsManager());
    }
}
