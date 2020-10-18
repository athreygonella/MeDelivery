package com.codebusters.medelivery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<String> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    public MyAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by Layout Manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView tv = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medical_item_text, parent, false);

        MyViewHolder vh = new MyViewHolder(tv);
        return vh;
    }

    // Replace the contents of a view (invoked by layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
