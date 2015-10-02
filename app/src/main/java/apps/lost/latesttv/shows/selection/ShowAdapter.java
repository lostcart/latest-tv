package apps.lost.latesttv.shows.selection;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import apps.lost.latesttv.R;
import apps.lost.latesttv.shows.Show;

/**
 * Adapter for displaying shows
 *
 * @author luke
 */
public class ShowAdapter extends RecyclerView.Adapter<ShowViewHolder> {

    private List<Show> mShows = new ArrayList<>();

    public void addShows(List<Show> shows) {
        mShows.addAll(shows);
        notifyDataSetChanged();
    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shows_item, parent, false);
        return new ShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShowViewHolder holder, final int position) {
        holder.setShow(mShows.get(position));
    }

    @Override
    public int getItemCount() {
        return mShows != null ? mShows.size() : 0;
    }
}