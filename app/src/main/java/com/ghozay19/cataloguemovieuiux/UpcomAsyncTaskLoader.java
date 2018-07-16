package com.ghozay19.cataloguemovieuiux;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.database.DefaultDatabaseErrorHandler;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.ghozay19.cataloguemovieuiux.BuildConfig.MOVIE_API_KEY;
import static com.ghozay19.cataloguemovieuiux.BuildConfig.MOVIE_URL;

public class UpcomAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieItem>> {

    private ArrayList<MovieItem> mData;
    private boolean mHasResult = false;

    public UpcomAsyncTaskLoader(final Context context, ArrayList<MovieItem> mData ) {
        super(context);
        onForceLoad();
    }

//    Default
//    public UpcomAsyncTaskLoader(Context context) {
//        super(context);
//    }

    @Override
    protected void onStartLoading(){
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }


    @Override
    public void deliverResult (final ArrayList<MovieItem> data){
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }


    @Override
    public void onReset(){
        super.onReset();
        onStopLoading();
        if (mHasResult){
            onReleaseResource(mData);
            mData = null;
            mHasResult = false;
        }
    }

    private void onReleaseResource(ArrayList<MovieItem> mData) {
    }


    @Override
    public ArrayList<MovieItem> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<MovieItem> movie_items = new ArrayList<>();
//        String url = MOVIE_URL +"/upcoming?api_key"+MOVIE_API_KEY+"&language=en-US";
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=" + MOVIE_API_KEY+ "&language=en-US" ;

        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart(){
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    String result = new String(responseBody);
                    JSONObject responObject = new JSONObject(result);
                    JSONArray list = responObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++){
                        JSONObject film = list.getJSONObject(i);
                        MovieItem movieItem = new MovieItem(film);
                        movie_items.add(movieItem);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });

        return movie_items;
    }
}
