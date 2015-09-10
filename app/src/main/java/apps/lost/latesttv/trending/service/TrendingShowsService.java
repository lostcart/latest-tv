package apps.lost.latesttv.trending.service;

import java.util.List;

import apps.lost.latesttv.shows.Show;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Interface for retrieving trending shows
 *
 * @author luke@ustwo.com
 */
public interface TrendingShowsService {

    @GET("/shows/trending")
    void getShows(Callback<List<Show>> callback);
}
