package apps.lost.latesttv.shows.selection;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import apps.lost.latesttv.LatestTVApplication;
import apps.lost.latesttv.R;
import apps.lost.latesttv.shows.Show;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * This displays trending shows from the api
 *
 * @author luke
 */
public class ShowsFragment extends Fragment implements ShowsPresenter.View {
    // How many columns should the recycler view use?
    private static final int GRID_COLUMNS_PORTRAIT = 2;
    private static final int GRID_COLUMNS_LANDSCAPE = 3;

    public static final String EXTRA_SHOW_TYPE = "extra_show_type";

    @Bind(R.id.trending_shows_recyclerview)
    RecyclerView mRecyclerView;

    // TODO: Use a dagger to inject this ?
    private ShowsPresenter mShowsPresenter;

    private ShowAdapter mShowAdapter;

    private GridLayoutManager mGridLayoutManager;

    public ShowsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.shows, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Check screen orientation
        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        int gridColumns = isPortrait? GRID_COLUMNS_PORTRAIT : GRID_COLUMNS_LANDSCAPE;

        // Setup recycler view settings
        mGridLayoutManager = new GridLayoutManager(getActivity(), gridColumns);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.addItemDecoration(new ShowItemDecoration(getActivity(), gridColumns, R.dimen.spacing, true));
        mRecyclerView.setAdapter(mShowAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = mGridLayoutManager.getChildCount();
                int totalItemCount = mGridLayoutManager.getItemCount();
                int firstVisibleItemPosition = mGridLayoutManager.findFirstVisibleItemPosition();

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                    mShowsPresenter.loadMoreItems();
                }
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Bundle bundle = getArguments();
        int showTypeId = bundle.getInt(EXTRA_SHOW_TYPE);
        mShowsPresenter = ((LatestTVApplication) getActivity().getApplication()).getPresenterModule()
                .getShowsPresenter(this, ShowsManager.SHOWS_TYPE.values()[showTypeId]);
        mShowsPresenter.onViewAttached();
        mShowAdapter = new ShowAdapter();
    }

    @Override
    public void showShows(List<Show> shows) {
        mShowAdapter.addShows(shows);
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), R.string.trending_shows_getshows_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(getActivity(), "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(getActivity(), "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}
