package com.example.mwael.scrollingnumper;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by moham on 6/5/2017.
 */

public abstract class MoRecyclerBaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected ArrayList<T> items;

    public MoRecyclerBaseAdapter(ArrayList<T> items) {
        this.items = items;
    }

    public void addItem(T item) {
        items.add(item);
        notifyItemInserted(this.items.size());
//        notifyDataSetChanged();
    }

    @Override
    public abstract void onBindViewHolder(VH holder, int position);

    public void addItem(int position, T item) {
        items.add(position, item);
        notifyItemInserted(position);

    }

    public void setItems(ArrayList<T> items) {
        if (this.items != null) {
            items.clear();
        }
        this.items = items;
        notifyDataSetChanged();
    }

    public void removeItem(T item) {
        if (items != null && items.contains(item)) {
            int position = items.indexOf(item);
            items.remove(item);
            notifyItemRemoved(position);
        }
    }

    public void removeItem(int position) {
        if (items != null) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clearItems(){
        if (this.items != null) {
            this.items.clear();
            notifyItemRangeRemoved(0, this.items.size());
        }
    }

    public void addMoreItems(ArrayList<T> items) {
        if (this.items != null) {
            int lastItem = this.items.size();
            this.items.addAll(items);
            notifyItemRangeInserted(lastItem, this.items.size());
        }
    }

    public void addMoreItemsUniquely(ArrayList<T> items) {
        try {
            ArrayList<T> intersection = new ArrayList<>(this.items);
            intersection.retainAll(items);
            items.removeAll(intersection);
            addMoreItems(items);
        } catch (UnsupportedOperationException e) {
            addMoreItems(items);
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public ArrayList<T> getItems() {
        return items;
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}