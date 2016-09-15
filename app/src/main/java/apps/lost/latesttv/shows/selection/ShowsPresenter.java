package apps.lost.latesttv.shows.selection;

import java.util.List;

import apps.lost.latesttv.shows.Show;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/**
 * Handles firing off events to the show manager, manipulating the data returned if needed
 * and returns data back to the view
 * view
 * <p/>
 * Created by luke
 */
public class ShowsPresenter {

    private static final int PAGE_SIZE = 20;

    private ShowsManager mShowsManager;

    private View mView;

    private ShowsManager.SHOWS_TYPE mShowsType;

    private boolean mLoadingShows;

    private int mCurrentPage;

    public ShowsPresenter(View view, ShowsManager showsManager, PublishSubject<List<Show>> onGotShowsSubject, ShowsManager.SHOWS_TYPE showsType) {
        mView = view;
        mShowsManager = showsManager;
        mShowsType = showsType;
        mShowsManager.setPublishSubject(onGotShowsSubject);

        onGotShowsSubject.subscribe(new Action1<List<Show>>() {
            @Override
            public void call(List<Show> aShows) {
                ShowsPresenter.this.gotShows(aShows);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                ShowsPresenter.this.failed();
            }
        });
    }

    /**
     * What should happen when the view is attached?
     */
    public void onViewAttached() {
        mCurrentPage = 1;
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
            mShowsManager.getShows(mShowsType, mCurrentPage, PAGE_SIZE);
        }
    }

    public void gotShows(List<Show> shows) {
        mLoadingShows = false;
        mView.showShows(shows);
    }

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
