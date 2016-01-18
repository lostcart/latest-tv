package apps.lost.latesttv.shows.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import apps.lost.latesttv.R;
import apps.lost.latesttv.shows.Show;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * This displays trending shows from the api
 *
 * @author luke
 */
public class ShowFragment extends Fragment {

    @Bind(R.id.show_imageview_image)
    ImageView mImageView;

    @Bind(R.id.show_textview_title)
    TextView mTitleTextView;

    @Bind(R.id.show_textview_year)
    TextView mYearTextView;

    @Bind(R.id.show_textview_overview)
    TextView mOverviewTextView;

    @Bind(R.id.show_textview_certification)
    TextView mCertifcationTextView;

    @Bind(R.id.show_textview_runtime)
    TextView mRunTimeTextView;

    private Show mShow;

    public ShowFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.show, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mTitleTextView.setText(mShow.getTitle());
        mYearTextView.setText(mShow.getReleaseYear());
        mCertifcationTextView.setText(mShow.getCertification());
        mOverviewTextView.setText(mShow.getOverview());
        mRunTimeTextView.setText(getString(R.string.show_runtime, mShow.getRunTime()));

        Picasso.with(mImageView.getContext()).load(mShow.getImageUrl()).fit().centerInside().into(mImageView);
    }

    public void setShow(Show show){
        mShow = show;
    }
}
