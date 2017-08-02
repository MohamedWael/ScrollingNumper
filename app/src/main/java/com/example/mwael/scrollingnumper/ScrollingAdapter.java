package com.example.mwael.scrollingnumper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mwael on 8/2/2017.
 */

public class ScrollingAdapter extends MoRecyclerEndlessAdapter<Integer, ScrollingAdapter.ScrollingNumberViewHolder> {


    private final RecyclerView recyclerViewObject;

    public ScrollingAdapter(ArrayList<Integer> items, RecyclerView mRecyclerView) {
        super(items, mRecyclerView);
        recyclerViewObject = mRecyclerView;
    }


    @Override
    public ScrollingNumberViewHolder onCreateMainViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.digit_layout, viewGroup, false);
        return new ScrollingNumberViewHolder(view);
    }

    @Override
    public void onBindMainViewHolder(ScrollingNumberViewHolder holder, int position) {
        holder.tvDigit.setText(getItem(position) + "");
    }

    public class ScrollingNumberViewHolder extends RecyclerView.ViewHolder {
        TextView tvDigit;

        public ScrollingNumberViewHolder(View itemView) {
            super(itemView);
            tvDigit = itemView.findViewById(R.id.tvDigit);
        }
    }
}
