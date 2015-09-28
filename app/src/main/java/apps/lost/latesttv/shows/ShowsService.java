package apps.lost.latesttv.shows;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Interface for retrieving trending shows
 *
 * @author luke@ustwo.com
 */
public interface ShowsService {

    @GET("/shows/trending?extended=images")
    void getTrending(Callback<List<Show>> callback);

    @GET("/shows/collected?extended=images")
    void getPopular(Callback<List<Show>> callback);

    @GET("/shows/watched?extended=images")
    void getWatched(Callback<List<Show>> callback);
}
