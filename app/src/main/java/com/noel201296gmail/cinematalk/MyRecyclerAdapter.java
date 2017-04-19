package com.noel201296gmail.cinematalk;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
/**
 * Created by OMOSEFE NOEL OBASEKI on 08/04/2017.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<MoviesFeeds1> feedsList;
    final private ListItemClickListener mOnClickListener;
    private Context context;
    private LayoutInflater inflater;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public MyRecyclerAdapter(Context context, List<MoviesFeeds1> feedsList, ListItemClickListener onClickListener) {

        this.context = context;
        this.feedsList = feedsList;
        mOnClickListener = onClickListener;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = inflater.inflate(R.layout.singleitem_recyclerview, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MoviesFeeds1 feeds = feedsList.get(position);
        //Pass the values of feeds object to Views
        holder.content.setText(feeds.getOriginalLanguage());
        Picasso.with(context)
                .load(feeds.getPosterPath())
                .placeholder(R.color.colorAccent)
                .into(holder.imageview);
        holder.ratingbar.setProgress(feeds.getVoteAverage().intValue());
        holder.title.setText(feeds.getTitle());

    }

    @Override
    public int getItemCount() {
        return feedsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView content, title;
        private ImageView imageview;
        private ProgressBar ratingbar;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.title_view);
            content = (TextView) itemView.findViewById(R.id.content_view);
            imageview = (ImageView) itemView.findViewById(R.id.thumbnail);
            ratingbar = (ProgressBar) itemView.findViewById(R.id.ratingbar_view);
            ratingbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
             Toast.makeText(context, "Rated By User : " + feedsList.get(getAdapterPosition()).getVoteAverage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public void onClick(View v) {
         MoviesFeeds1 m = feedsList.get(getAdapterPosition());
            Intent intent = new Intent(context, DisplayMovies.class);
            intent.putExtra("title", m.getTitle());
            intent.putExtra( "backdrop_path",m.getBackdropPath());
            intent.putExtra("overview", m.getOverview());
            intent.putExtra("vote_average",String.valueOf(m .getVoteAverage()));
            intent.putExtra("release_date", m.getReleaseDate());
           context.startActivity(intent);
        }
    }

}