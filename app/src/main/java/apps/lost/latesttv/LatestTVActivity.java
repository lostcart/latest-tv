package apps.lost.latesttv;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import apps.lost.latesttv.shows.ShowsFragment;
import apps.lost.latesttv.shows.ShowsManager;

/**
 * Handles displaying the different shows fragment
 *
 * @author luke
 */
public class LatestTVActivity extends AppCompatActivity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    private int[] mPages = {R.string.title_popular, R.string.title_trending, R.string.title_most_watched};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.latest_tv);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.latest_tv_viewpager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ShowsFragment fragment = new ShowsFragment();
            Bundle arguments = new Bundle();

            ShowsManager.SHOWS_TYPE shows_type = null;
            switch (mPages[position]) {
                case R.string.title_popular:
                    shows_type = ShowsManager.SHOWS_TYPE.POPULAR;
                    break;
                case R.string.title_trending:
                    shows_type = ShowsManager.SHOWS_TYPE.TRENDING;
                    break;
                case R.string.title_most_watched:
                    shows_type = ShowsManager.SHOWS_TYPE.MOST_WATCHED;
                    break;
            }

            arguments.putInt(ShowsFragment.EXTRA_SHOW_TYPE, shows_type.ordinal());
            fragment.setArguments(arguments);
            return fragment;
        }

        @Override
        public int getCount() {
            return mPages.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(mPages[position]);
        }
    }
}
