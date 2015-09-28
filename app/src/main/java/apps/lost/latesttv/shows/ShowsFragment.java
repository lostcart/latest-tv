package apps.lost.latesttv.shows;

import android.app.Activity;
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
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * This displays trending shows from the api
 *
 * @author luke
 */
public class ShowsFragment extends Fragment implements ShowsPresenter.View {
    // How many columns should the recycler view use?
    private static final int GRID_COLUMNS = 2;

    public static final String EXTRA_SHOW_TYPE = "extra_show_type";

    @Bind(R.id.trending_shows_recyclerview)
    RecyclerView mRecyclerView;

    // TODO: Use a dagger to inject this ?
    private ShowsPresenter mShowsPresenter;

    private ShowAdapter mShowAdapter;

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
        // Setup recycler view settings
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), GRID_COLUMNS));
        mRecyclerView.addItemDecoration(new ShowItemDecoration(getActivity(), GRID_COLUMNS, R.dimen.spacing, true));
        mRecyclerView.setAdapter(mShowAdapter);
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
        mShowAdapter.setShows(shows);
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), R.string.trending_shows_getshows_error, Toast.LENGTH_LONG).show();
    }
}
