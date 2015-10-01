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

    private static final int PAGE_SIZE = 20;

    private ShowsManager mShowsManager;

    private View mView;

    private ShowsManager.SHOWS_TYPE mSHOWSType;

    private boolean mLoadingShows;

    private int mCurrentPage;

    public ShowsPresenter(View view, ShowsManager showsManager, ShowsManager.SHOWS_TYPE shows_type) {
        mView = view;
        mShowsManager = showsManager;
        mSHOWSType = shows_type;
    }

    /**
     * What should happen when the view is attached?
     */
    public void onViewAttached() {
        mCurrentPage = 0;
        getItems();
    }

    /**
     * Increase the current page count and get more items
     */
    public void loadMoreItems() {
        if (!mLoadingShows) {
            mCurrentPage++;
            getItems();
        }
    }

    /**
     * Load more items if we're not already loading shows
     */
    private void getItems() {
        if (!mLoadingShows) {
            mLoadingShows = true;
            mShowsManager.getShows(this, mSHOWSType, mCurrentPage, PAGE_SIZE);
        }
    }

    @Override
    public void gotShows(List<Show> shows) {
        mLoadingShows = false;
        mView.showShows(shows);
    }

    @Override
    public void failed() {
        mLoadingShows = false;
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
