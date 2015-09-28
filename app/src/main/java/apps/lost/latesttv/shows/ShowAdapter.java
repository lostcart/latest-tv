package apps.lost.latesttv.shows;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import apps.lost.latesttv.R;

/**
 * Adapter for displaying shows
 *
 * @author luke
 */
public class ShowAdapter extends RecyclerView.Adapter<ShowViewHolder> {

    private List<Show> mShows;

    public void setShows(List<Show> shows) {
        mShows = shows;
        notifyDataSetChanged();
    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_item, parent, false);
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