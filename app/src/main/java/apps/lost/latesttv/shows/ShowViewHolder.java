package apps.lost.latesttv.shows;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import apps.lost.latesttv.R;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * View holder for handling each show item
 *
 * @author luke
 */
public class ShowViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.show_item_textview_title)
    TextView mTitleTextView;

    public ShowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    // TODO: This feels wrong when using MVP
    public void setShow(Show show) {
        mTitleTextView.setText(show.getTitle());
    }
}