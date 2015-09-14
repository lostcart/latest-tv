import android.test.InstrumentationTestCase;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import apps.lost.latesttv.shows.Show;
import apps.lost.latesttv.trending.TrendingShowsPresenter;
import apps.lost.latesttv.trending.service.TrendingShowsManager;

/**
 * Created by luke
 */
public class TrendingShowsPresenterTests extends InstrumentationTestCase {

    private TrendingShowsPresenter.View mView = Mockito.mock(TrendingShowsPresenter.View.class);
    private TrendingShowsManager mTrendingShowsManager = Mockito.mock(TrendingShowsManager.class);

    public void test_gotShows_Valid_CallsShowShows() {

        final List<Show> shows = new ArrayList<>();
        final TrendingShowsPresenter presenter = new TrendingShowsPresenter(mView, mTrendingShowsManager);

        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                presenter.gotShows(shows);
                return null;
            }
        }).when(mTrendingShowsManager).getShows(Mockito.any(TrendingShowsManager.TrendingShowsCallback.class));

        presenter.onViewAttached();

        Mockito.verify(mView).showShows(shows);
    }

    public void test_gotShows_Invalid_CallsFailed() {
        final TrendingShowsPresenter presenter = new TrendingShowsPresenter(mView, mTrendingShowsManager);

        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                presenter.failed();
                return null;
            }
        }).when(mTrendingShowsManager).getShows(Mockito.any(TrendingShowsManager.TrendingShowsCallback.class));

        presenter.onViewAttached();

        Mockito.verify(mView).showError();
    }
}
