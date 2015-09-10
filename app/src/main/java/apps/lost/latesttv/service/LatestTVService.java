package apps.lost.latesttv.service;

import apps.lost.latesttv.BuildConfig;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;

/**
 * Helper class for creating web service helpers.
 *
 * @author luke@ustwo.com
 */
public class LatestTVService {

    /**
     * Return a service with the given service class
     *
     * @param serviceClass The service you wish to use
     * @param endPoint     The endpoint for the service
     * @param <T>
     * @return
     */
    public static <T> T getService(Class<T> serviceClass, String endPoint) {
        RequestInterceptor mRequestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                //request.addHeader("Content-Type", "application/json");
                request.addHeader("trakt-api-version", "2");
                request.addHeader("trakt-api-key", BuildConfig.API_KEY);
            }
        };
        return new RestAdapter.Builder().setEndpoint(endPoint).setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog("LatestTVService")).setRequestInterceptor(mRequestInterceptor).build().create(serviceClass);
    }

    public static <T> T getService(Class<T> serviceClass) {
        return getService(serviceClass, BuildConfig.API_URL);
    }
}
