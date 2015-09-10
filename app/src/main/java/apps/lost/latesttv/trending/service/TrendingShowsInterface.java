package apps.lost.latesttv.trending.service;

import java.util.List;

import apps.lost.latesttv.shows.Show;

/**
 * Created by luke
 */
public interface TrendingShowsInterface {

    void gotShows(List<Show> shows);

    void failed();
}
