package apps.lost.latesttv.shows.selection;

import java.util.List;

import apps.lost.latesttv.service.LatestTVService;
import apps.lost.latesttv.shows.Show;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Handles the logic of getting the shows and what we should do with the possible responses
 * <p/>
 * Created by luke
 */
public class ShowsManager {

    PublishSubject<List<Show>> onGotShowsSubject;

    public void setPublishSubject(PublishSubject<List<Show>> onGotShowsSubject) {
        this.onGotShowsSubject = onGotShowsSubject;
    }

    public enum SHOWS_TYPE {
        TRENDING,
        POPULAR,
        MOST_WATCHED
    }

    /**
     * Go grab the shows!
     */
    public void getShows(SHOWS_TYPE shows_type, int page, int pageSize) {
        ShowsService showsService = LatestTVService.getService(ShowsService.class);

        Observer observer = new Observer<List<Show>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                onGotShowsSubject.onError(null);
            }

            @Override
            public void onNext(List<Show> shows) {
                onGotShowsSubject.onNext(shows);
            }
        };

        switch (shows_type) {
            case MOST_WATCHED:
                showsService.getWatched(page, pageSize).subscribeOn(Schedulers.newThread()).observeOn(
                        AndroidSchedulers.mainThread()).subscribe(observer);
                break;
            case TRENDING:
                showsService.getTrending(page, pageSize).subscribeOn(Schedulers.newThread()).observeOn(
                        AndroidSchedulers.mainThread()).subscribe(observer);
                break;
            case POPULAR:
                showsService.getPopular(page, pageSize).subscribeOn(Schedulers.newThread()).observeOn(
                        AndroidSchedulers.mainThread()).subscribe(observer);
                break;
        }
    }
}
