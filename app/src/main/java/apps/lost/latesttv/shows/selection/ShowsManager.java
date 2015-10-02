package apps.lost.latesttv.shows.selection;

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
public class ShowsManager {

    public enum SHOWS_TYPE {
        TRENDING,
        POPULAR,
        MOST_WATCHED
    }

    /**
     * Go grab the shows!
     */
    public void getShows(final ShowsCallback showsCallback, SHOWS_TYPE shows_type, int page, int pageSize) {
        ShowsService showsService = LatestTVService.getService(ShowsService.class);

        Callback<List<Show>> callback = new Callback<List<Show>>() {
            @Override
            public void success(List<Show> shows, Response response) {
                showsCallback.gotShows(shows);
            }

            @Override
            public void failure(RetrofitError error) {
                showsCallback.failed();
            }
        };

        switch (shows_type) {
            case MOST_WATCHED:
                showsService.getWatched(page, pageSize,callback);
                break;
            case TRENDING:
                showsService.getTrending(page, pageSize,callback);
                break;
            case POPULAR:
                showsService.getPopular(page, pageSize,callback);
                break;
        }
    }

    public interface ShowsCallback {
        void gotShows(List<Show> shows);

        void failed();
    }
}
