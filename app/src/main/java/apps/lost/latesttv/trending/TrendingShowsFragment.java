package apps.lost.latesttv.trending;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import apps.lost.latesttv.R;
import apps.lost.latesttv.shows.Show;
import apps.lost.latesttv.shows.ShowAdapter;
import apps.lost.latesttv.shows.ShowItemDecoration;
import apps.lost.latesttv.trending.service.TrendingShowsManager;
import apps.lost.latesttv.trending.service.TrendingShowsPresenter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * This displays trending shows from the api
 *
 * @author luke
 */
public class TrendingShowsFragment extends Fragment implements TrendingShowsPresenter.View {
    // How many columns should the recycler view use?
    private static final int GRID_COLUMNS = 2;

    @Bind(R.id.trending_shows_recyclerview)
    RecyclerView mRecyclerView;

    // TODO: Use dagger to inject this ?
    private TrendingShowsPresenter mTrendingShowsPresenter;

    public TrendingShowsFragment() {
        mTrendingShowsPresenter = new TrendingShowsPresenter(this, new TrendingShowsManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trending_shows, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup recycler view settings
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), GRID_COLUMNS));
        mRecyclerView.addItemDecoration(new ShowItemDecoration(getActivity(), GRID_COLUMNS, R.dimen.spacing, true));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mTrendingShowsPresenter.onViewAttached();
    }

    @Override
    public void showShows(List<Show> shows) {
        mRecyclerView.setAdapter(new ShowAdapter(shows));
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), R.string.trending_shows_getshows_error, Toast.LENGTH_LONG).show();
    }
}
