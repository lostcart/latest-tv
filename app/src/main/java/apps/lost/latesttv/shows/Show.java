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

    private class ShowDetails {
        @SerializedName("title")
        public String mTitle;

        @SerializedName("year")
        public String mReleaseYear;

        @SerializedName("ids")
        public ShowIDs mShowIDs;

        private class ShowIDs {
            @SerializedName("imdb")
            public String mIMDBid;
        }
    }
}
