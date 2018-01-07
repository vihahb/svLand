package com.goldit.managerinfo.coreapi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter<T>.ViewHolder> {
    private List<T> items = new ArrayList<>();
    View view;
    OnItemClickListener onItemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(getLayoutItem(), parent, false);
        return new ViewHolder(view);
    }

    protected abstract int getLayoutItem();

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populate(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateList(List<T> thingList) {
        items.clear();
        items.addAll(thingList);
        notifyDataSetChanged();
    }


    public static interface OnItemClickListener<T> {
        public void onItemClick(View view, T item, int adapterPosition, boolean isLongClick);
    }

    public void setOnItemClickListener(final OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(T item, int position) {

        }


    }
}
