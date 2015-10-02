package apps.lost.latesttv.shows.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import apps.lost.latesttv.R;
import apps.lost.latesttv.shows.Show;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Handles displaying the details for a particular show
 *
 * @author luke
 */
public class ShowActivity extends AppCompatActivity {

    @Bind(R.id.show_imageview_image)
    ImageView mImageView;

    @Bind(R.id.show_textview_title)
    TextView mTitleTextView;

    @Bind(R.id.show_textview_year)
    TextView mYearTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        ButterKnife.bind(this);

        setupView();
    }

    private void setupView() {
        Show show = EventBus.getDefault().removeStickyEvent(Show.class);

        mTitleTextView.setText(show.getTitle());
        mYearTextView.setText(show.getReleaseYear());
        Glide.with(mImageView.getContext()).load(show.getImageUrl()).fitCenter().into(mImageView);
    }
}
