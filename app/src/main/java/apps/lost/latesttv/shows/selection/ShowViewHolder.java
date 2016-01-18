package apps.lost.latesttv.shows.selection;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import apps.lost.latesttv.R;
import apps.lost.latesttv.shows.Show;
import apps.lost.latesttv.shows.details.ShowActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


/**
 * View holder for handling each show item
 *
 * @author luke
 */
public class ShowViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.show_item_imageview_image)
    ImageView mImageView;

    @Bind(R.id.show_item_textview_title)
    TextView mTitleTextView;

    @Bind(R.id.show_item_textview_year)
    TextView mYearTextView;

    View mView;

    public ShowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        mView = itemView;
    }

    public void setShow(final Show show) {
        mTitleTextView.setText(show.getTitle());
        mYearTextView.setText(show.getReleaseYear());

        Picasso.with(mImageView.getContext()).load(show.getImageUrl()).fit().centerInside().into(mImageView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Post the show so we can access it from the details screen
                EventBus.getDefault().postSticky(show);

                // Set transition settings for opening the details screen
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation((Activity) v.getContext(), mImageView,
                                v.getContext().getString(R.string.transition_show_image));
                v.getContext().startActivity(new Intent(v.getContext(), ShowActivity.class), options.toBundle());
            }
        });
    }
}