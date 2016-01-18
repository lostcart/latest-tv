package apps.lost.latesttv.shows;

import com.google.gson.annotations.SerializedName;

/**
 * Show model
 * <p/>
 * <p/>
 * Example show json
 * <p/>
 * {
 * "watcher_count": 2933,
 * "play_count": 140919,
 * "collected_count": 28346,
 * "collector_count": 275,
 * "show": {
 * "title": "The Big Bang Theory",
 * "year": 2007,
 * "ids": {
 * "trakt": 1409,
 * "slug": "the-big-bang-theory",
 * "tvdb": 80379,
 * "imdb": "tt0898266",
 * "tmdb": 1418,
 * "tvrage": 8511
 * }
 * }
 * }
 *
 * @author luke
 */
public class Show {

    @SerializedName("show")
    private ShowDetails mShowDetails;

    public String getTitle() {
        return mShowDetails.mTitle;
    }

    public String getReleaseYear() {
        return mShowDetails.mReleaseYear;
    }

    public String getIMDBId() {
        return mShowDetails.mShowIDs.mIMDBid;
    }

    public String getImageUrl() {
        return mShowDetails.mShowImages.mPosterImages.mThumbnailImage;
    }

    public String getOverview() {
        return mShowDetails.mOverview;
    }

    public String getCertification() {
        return mShowDetails.mCertification;
    }

    public int getRunTime() {
        return mShowDetails.mRunTime;
    }

    private class ShowDetails {
        @SerializedName("title")
        public String mTitle;

        @SerializedName("year")
        public String mReleaseYear;

        @SerializedName("ids")
        public ShowIDs mShowIDs;

        @SerializedName("images")
        public ShowImages mShowImages;

        @SerializedName("overview")
        public String mOverview;

        @SerializedName("certification")
        public String mCertification;

        @SerializedName("rating")
        public float mRating;

        @SerializedName("runtime")
        public int mRunTime;

        private class ShowIDs {
            @SerializedName("imdb")
            public String mIMDBid;
        }

        private class ShowImages {
            @SerializedName("poster")
            public Images mPosterImages;

            private class Images {
                @SerializedName("thumb")
                public String mThumbnailImage;
            }
        }
    }
}
