package com.ghozay19.cataloguemovieuiux;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NowPlayAdapter extends RecyclerView.Adapter<NowPlayAdapter.ViewHolder> {

    private ArrayList<MovieItem> mMovieItem = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public static String EXTRA_TITLE = "extra_title";
    public static String EXTRA_OVERVIEW = "extra_overview";
    public static String EXTRA_RELEASE_DATE = "extra_release_date";
    public static String EXTRA_POSTER_JPG = "extra_poster_jpg";
    public static String EXTRA_RATE = "extra_rate";
    public static String EXTRA_ITEMS = "movie_items";


    public NowPlayAdapter(final Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItem> items) {
        mMovieItem = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItem item) {
        mMovieItem.add(item);
        notifyDataSetChanged();
    }



    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    public MovieItem getItem(int position) {
        return mMovieItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mMovieItem == null) return 0;
        return mMovieItem.size();
    }



    public void clearData() {
        mMovieItem.clear();
        notifyDataSetChanged();
    }

    public void replaceAll(ArrayList<MovieItem> items) {
        mMovieItem.clear();
        mMovieItem = items;
        notifyDataSetChanged();
    }

    public void updateData(ArrayList<MovieItem> items) {
        mMovieItem.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_detail, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Picasso.with(context).load("http://image.tmdb.org/t/p/w154/" + mMovieItem.get(position).getMov_poster()).placeholder(context.getResources().getDrawable(R.drawable.ic_launcher_background)).error(context.getResources().getDrawable(R.drawable.image)).into(holder.iv_poster_movie);
//        Picasso.with(context).load(mMovieItem.get(position).getMov_poster()).into(holder.iv_poster_movie);
        holder.tv_title_movie.setText(mMovieItem.get(position).getMov_title());
        holder.tv_synopsis_movie.setText(mMovieItem.get(position).getMov_synopsis());
        holder.tv_rlsdate_movie.setText(mMovieItem.get(position).getMov_releasedate());
        holder.tv_title_movie.setText(mMovieItem.get(position).getMov_title());


        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailActivity.class);
                Bundle extras = new Bundle();
                extras.putString(EXTRA_TITLE, mMovieItem.get(position).getMov_title());
                extras.putString(EXTRA_POSTER_JPG, mMovieItem.get(position).getMov_poster());
                extras.putString(EXTRA_OVERVIEW, mMovieItem.get(position).getMov_synopsis());
                extras.putString(EXTRA_RELEASE_DATE, mMovieItem.get(position).getMov_releasedate());
                extras.putString(EXTRA_RATE, mMovieItem.get(position).getMov_rate());
                intent.putExtra(EXTRA_ITEMS, extras);
                context.startActivity(intent);
            }
        });

        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, (context.getString(R.string.label_share)) + " " + mMovieItem.get(position).getMov_title(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_poster_movie;
        TextView tv_title_movie;
        TextView tv_synopsis_movie;
        TextView tv_rlsdate_movie;
        Button btn_detail;
        Button btn_share;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_poster_movie = (ImageView) itemView.findViewById(R.id.iv_poster_cv);
            tv_title_movie = (TextView) itemView.findViewById(R.id.title_detail_cv);
            tv_synopsis_movie = (TextView) itemView.findViewById(R.id.overview_detail_cv);
            tv_rlsdate_movie = (TextView) itemView.findViewById(R.id.release_date_detail_cv);
            btn_detail = (Button) itemView.findViewById(R.id.btn_detail);
            btn_share = (Button)itemView.findViewById(R.id.btn_share);
        }

    }
}