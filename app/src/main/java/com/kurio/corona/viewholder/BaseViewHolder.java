package com.kurio.corona.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<W> extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected W mData = null;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public abstract void setData(W data);
}
