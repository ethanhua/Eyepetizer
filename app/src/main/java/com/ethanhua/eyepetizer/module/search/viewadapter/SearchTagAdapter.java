package com.ethanhua.eyepetizer.module.search.viewadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ethanhua.eyepetizer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ethanhua on 2017/10/24.
 */

public class SearchTagAdapter extends RecyclerView.Adapter<SearchTagAdapter.ViewHolder> {

    private final List<String> strList =new ArrayList<>();
    private OnItemClickListener mItemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_text_wrap, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(strList.get(position));
        holder.itemView.setOnClickListener(v -> {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(holder.textView.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return strList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.text);
        }
    }

    public void setItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(String query);
    }

    public void replace(List<String> data){
        strList.clear();
        strList.addAll(data);
        notifyDataSetChanged();
    }
}
