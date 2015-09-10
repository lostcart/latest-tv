package apps.lost.latesttv.trending.service;

import java.util.List;

import apps.lost.latesttv.service.LatestTVService;
import apps.lost.latesttv.shows.Show;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Handles the logic of getting the shows and what we should do with the possible responses
 * <p/>
 * Created by luke
 */
public class TrendingShowsManager {

    private TrendingShowsInterface mTrendingShowsInterface;

    public TrendingShowsManager(TrendingShowsInterface trendingShowsInterface) {
        mTrendingShowsInterface = trendingShowsInterface;
    }

    /**
     * Go grab the shows!
     */
    public void getShows() {
        TrendingShowsService trendingShowsService = LatestTVService.getService(TrendingShowsService.class);
        trendingShowsService.getShows(new Callback<List<Show>>() {
            @Override
            public void success(List<Show> shows, Response response) {
                //TODO: Extra validation of shows here if needed?
                mTrendingShowsInterface.gotShows(shows);
            }

            @Override
            public void failure(RetrofitError error) {
                mTrendingShowsInterface.failed();
            }
        });
    }
}
