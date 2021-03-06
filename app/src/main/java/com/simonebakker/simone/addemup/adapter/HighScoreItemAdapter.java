package com.simonebakker.simone.addemup.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simonebakker.simone.addemup.R;
import com.simonebakker.simone.addemup.models.Game;

import java.util.List;

public class HighScoreItemAdapter extends RecyclerView.Adapter<HighScoreViewHolder> {

    private Context mContext;
    private List<Game> mGameArrayList;

    public HighScoreItemAdapter(Context context, List<Game> list) {
        this.mGameArrayList = list;
        this.mContext = context;
    }

    @Override
    public HighScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.high_score_item, parent, false);
        return new HighScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HighScoreViewHolder holder, final int position) {
        final Game game = mGameArrayList.get(position);

        // show the score and name of the user
        holder.scoreView.setText(String.valueOf(game.getmPoints()));
        String name = game.getmName();
        if (name.isEmpty()) {
            name = mContext.getString(R.string.anonymous);
        }
        holder.nameView.setText(name);

        // shows the date & time on two lines
        String dateTime = game.getmDate();
        String[] dateTimeArray = dateTime.split(" ");
        holder.dateView.setText(dateTimeArray[0] + "\n" + dateTimeArray[1]);
    }

    @Override
    public int getItemCount() {
        return mGameArrayList.size();
    }
}
