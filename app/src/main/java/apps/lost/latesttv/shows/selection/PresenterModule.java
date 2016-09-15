package apps.lost.latesttv.shows.selection;

import java.util.List;

import apps.lost.latesttv.shows.Show;
import rx.subjects.PublishSubject;

/**
 * Module to get the show presenter
 * Created by luke
 */
public class PresenterModule {

    PublishSubject<List<Show>> gotShowsSubject = PublishSubject.create();

    public PresenterModule() {

    }

    public ShowsPresenter getShowsPresenter(ShowsPresenter.View view, ShowsManager.SHOWS_TYPE shows_type) {
        return new ShowsPresenter(view, new ShowsManager(), gotShowsSubject, shows_type);
    }
}
