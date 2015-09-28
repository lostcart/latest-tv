package apps.lost.latesttv.shows;

import java.util.List;

/**
 * Handles firing off events to the show manager, manipulating the data returned if needed
 * and returns data back to the view
 * view
 * <p/>
 * Created by luke
 */
public class ShowsPresenter implements ShowsManager.ShowsCallback {

    private ShowsManager mShowsManager;

    private View mView;

    private ShowsManager.SHOWS_TYPE mSHOWSType;

    public ShowsPresenter(View view, ShowsManager showsManager, ShowsManager.SHOWS_TYPE shows_type) {
        mView = view;
        mShowsManager = showsManager;
        mSHOWSType = shows_type;
    }

    public void onViewAttached() {
        mShowsManager.getShows(this, mSHOWSType);
    }

    @Override
    public void gotShows(List<Show> shows) {
        mView.showShows(shows);
    }

    @Override
    public void failed() {
        mView.showError();
    }

    /**
     * Interface for declaring all the things that the view
     * should be able to do, that we want to control from here..
     */
    public interface View {
        void showShows(List<Show> shows);

        void showError();
    }
}
