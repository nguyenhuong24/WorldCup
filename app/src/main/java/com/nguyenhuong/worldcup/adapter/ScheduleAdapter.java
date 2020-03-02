package com.nguyenhuong.worldcup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nguyenhuong.worldcup.R;
import com.nguyenhuong.worldcup.modal.Schedule;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private List<Schedule> mSchedules;
    private Context mContext;

    public ScheduleAdapter(List<Schedule> mSchedules, Context mContext) {
        this.mSchedules = mSchedules;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.schedule_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder holder, int position) {
        Schedule schedule = mSchedules.get(position);
        Glide.with(mContext).load(schedule.getQuocky1()).into(holder.quocKy1);
        Glide.with(mContext).load(schedule.getQuocky2()).into(holder.quocKy2);
        holder.doi1.setText(schedule.getDoi1());
        holder.doi2.setText(schedule.getDoi2());
        holder.ngay.setText(schedule.getNgay());
        holder.kenh.setText(schedule.getKenh());
        holder.vong.setText(schedule.getVong());
        holder.gio.setText(schedule.getGio());


    }

    @Override
    public int getItemCount() {
        return mSchedules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView quocKy1, quocKy2;
        TextView doi1, doi2, ngay, gio, vong, kenh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quocKy1 = itemView.findViewById(R.id.cimg_quocky1);
            quocKy2 = itemView.findViewById(R.id.cimg_quocky2);
            doi1 = itemView.findViewById(R.id.tv_doi1);
            doi2 = itemView.findViewById(R.id.tv_doi2);
            ngay = itemView.findViewById(R.id.tv_ngay);
            gio = itemView.findViewById(R.id.tv_gio);
            vong = itemView.findViewById(R.id.tv_vong);
            kenh = itemView.findViewById(R.id.tv_kenh);

        }
    }
}
