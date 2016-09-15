package apps.lost.latesttv.shows.selection;

import java.util.List;

import apps.lost.latesttv.shows.Show;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Interface for retrieving trending shows
 *
 * @author luke@ustwo.com
 */
public interface ShowsService {

    @GET("/shows/trending?extended=full,images")
    Observable<List<Show>> getTrending(@Query("page")int page, @Query("limit")int pageSize);

    @GET("/shows/collected?extended=full,images")
    Observable<List<Show>>  getPopular(@Query("page")int page, @Query("limit")int pageSize);

    @GET("/shows/watched?extended=full,images")
    Observable<List<Show>>  getWatched(@Query("page")int page, @Query("limit")int pageSize);
}
