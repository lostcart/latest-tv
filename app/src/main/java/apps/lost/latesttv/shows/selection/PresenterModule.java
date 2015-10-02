package apps.lost.latesttv.shows.selection;

/**
 * Module to get the show presenter
 * Created by luke
 */
public class PresenterModule {

    public ShowsPresenter getShowsPresenter(ShowsPresenter.View view, ShowsManager.SHOWS_TYPE shows_type) {
        return new ShowsPresenter(view, new ShowsManager(), shows_type);
    }
}
