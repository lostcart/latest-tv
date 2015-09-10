package apps.lost.latesttv.trending;

import android.app.Fragment;
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
import apps.lost.latesttv.trending.service.TrendingShowsInterface;
import apps.lost.latesttv.trending.service.TrendingShowsManager;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * This displays trending shows from the api
 *
 * @author luke
 */
public class TrendingShowsFragment extends Fragment implements TrendingShowsInterface {
    private static final int GRID_COLUMNS = 2;

    @Bind(R.id.trending_shows_recyclerview)
    RecyclerView mRecyclerView;

    // TODO: Use dagger to inject this ?
    private TrendingShowsManager mTrendingShowsManager;

    public TrendingShowsFragment() {
        mTrendingShowsManager = new TrendingShowsManager(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trending_shows, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), GRID_COLUMNS));
        mRecyclerView.addItemDecoration(new ShowItemDecoration(getActivity(), GRID_COLUMNS, R.dimen.spacing, true));

        // Try and grab the data we need
        mTrendingShowsManager.getShows();
    }

    @Override
    public void gotShows(List<Show> shows) {
        // TODO: hmmm
        mRecyclerView.setAdapter(new ShowAdapter(shows));
    }

    @Override
    public void failed() {
        //TODO: getContext() not working in this fragment??
        Toast.makeText(getActivity(), R.string.trending_shows_getshows_error, Toast.LENGTH_LONG).show();
    }
}
