package com.ghozay19.cataloguemovieuiux;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItem>> {

    NowPlayAdapter adapter;
    Context context;
    RecyclerView mRecyclerView;
    private ArrayList<MovieItem> nowPlayingData;



    public NowPlayingFragment() {
        // Required empty public constructor
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing,container,false);//cardview
        context = view.getContext();

        mRecyclerView = (RecyclerView)view.findViewById(R.id.rv_now_playing);

        adapter = new NowPlayAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(adapter);
        getLoaderManager().initLoader(0, null,this);
        return view;
    }


    @Override
    public Loader<ArrayList<MovieItem>> onCreateLoader(int id, Bundle args) {
        return new NowPlayAsyncTaskLoader(getContext(), nowPlayingData);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItem>> loader, ArrayList<MovieItem> nowPlayingData) {
        adapter.setData(nowPlayingData);  //before
//        adapter.setData(data); //after ask in Discussion
    }

    @Override
    public void onLoaderReset( Loader<ArrayList<MovieItem>> loader) {
        adapter.setData(null);

    }
}
