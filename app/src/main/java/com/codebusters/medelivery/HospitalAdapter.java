package com.codebusters.medelivery;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class HospitalAdapter extends RecyclerView.Adapter<com.codebusters.medelivery.HospitalAdapter.MyViewHolder> {
    private ArrayList<Hospital> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;

        public MyViewHolder(LinearLayout linearLayout) {
            super(linearLayout);
            this.linearLayout = linearLayout;
        }
    }

    public HospitalAdapter(ArrayList<Hospital> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by Layout Manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hospital_item, parent, false);

        MyViewHolder vh = new MyViewHolder(linearLayout);
        return vh;
    }

    // Replace the contents of a view (invoked by layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ((TextView) holder.linearLayout.findViewById(R.id.tvHospitalName)).setText(mDataset.get(position).getName());
        ((Button) holder.linearLayout.findViewById(R.id.btnAppointment)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), AppointmentActivity.class);
//                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
