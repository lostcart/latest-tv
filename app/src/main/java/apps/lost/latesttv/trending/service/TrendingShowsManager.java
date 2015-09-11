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

    /**
     * Go grab the shows!
     */
    public void getShows(final TrendingShowsCallback callback) {
        TrendingShowsService trendingShowsService = LatestTVService.getService(TrendingShowsService.class);
        trendingShowsService.getShows(new Callback<List<Show>>() {
            @Override
            public void success(List<Show> shows, Response response) {
                //TODO: Extra validation of shows here if needed?
                callback.gotShows(shows);
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failed();
            }
        });
    }

    public interface TrendingShowsCallback {

        void gotShows(List<Show> shows);

        void failed();
    }
}
