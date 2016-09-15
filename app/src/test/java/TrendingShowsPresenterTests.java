import android.test.InstrumentationTestCase;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import apps.lost.latesttv.shows.Show;
import apps.lost.latesttv.shows.selection.ShowsManager;
import apps.lost.latesttv.shows.selection.ShowsPresenter;

/**
 * Created by luke
 */
public class TrendingShowsPresenterTests extends InstrumentationTestCase {

    private ShowsPresenter.View mView = Mockito.mock(ShowsPresenter.View.class);
    private ShowsManager mTrendingShowsManager = Mockito.mock(ShowsManager.class);

    public void test_gotShows_Valid_CallsShowShows() {

        final List<Show> shows = new ArrayList<>();
        final ShowsPresenter presenter = new ShowsPresenter(mView, mTrendingShowsManager, ShowsManager.SHOWS_TYPE.MOST_WATCHED);

        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                presenter.gotShows(shows);
                return null;
            }
        }).when(mTrendingShowsManager).getShows(Mockito.any(ShowsManager.ShowsCallback.class), ShowsManager.SHOWS_TYPE.MOST_WATCHED, 0,1);

        presenter.onViewAttached();

        Mockito.verify(mView).showShows(shows);
    }

    public void test_gotShows_Invalid_CallsFailed() {
        final ShowsPresenter presenter = new ShowsPresenter(mView, mTrendingShowsManager, ShowsManager.SHOWS_TYPE.MOST_WATCHED);

        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                presenter.failed();
                return null;
            }
        }).when(mTrendingShowsManager).getShows(Mockito.any(ShowsManager.ShowsCallback.class), ShowsManager.SHOWS_TYPE.MOST_WATCHED, 0,1);

        presenter.onViewAttached();

        Mockito.verify(mView).showError();
    }
}
