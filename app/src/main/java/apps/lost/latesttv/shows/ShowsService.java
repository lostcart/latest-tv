package apps.lost.latesttv.shows;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Interface for retrieving trending shows
 *
 * @author luke@ustwo.com
 */
public interface ShowsService {

    @GET("/shows/trending?extended=full,images")
    void getTrending(@Query("page")int page, @Query("limit")int pageSize, Callback<List<Show>> callback);

    @GET("/shows/collected?extended=full,images")
    void getPopular(@Query("page")int page, @Query("limit")int pageSize, Callback<List<Show>> callback);

    @GET("/shows/watched?extended=full,images")
    void getWatched(@Query("page")int page, @Query("limit")int pageSize, Callback<List<Show>> callback);
}
