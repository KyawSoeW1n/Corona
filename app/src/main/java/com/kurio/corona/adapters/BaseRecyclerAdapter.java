package com.kurio.corona.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kurio.corona.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T, W> extends RecyclerView.Adapter<BaseViewHolder<W>> {
    protected ArrayList<W> mData = null;
    protected LayoutInflater layoutInflater;

    public BaseRecyclerAdapter(Context context) {
        this.mData = new ArrayList<W>();
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<W> holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addNewData(List<W> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void removeData(List<W> data) {
        mData.removeAll(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }


}
