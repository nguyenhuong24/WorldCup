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
import com.nguyenhuong.worldcup.modal.Player;
import com.nguyenhuong.worldcup.modal.Schedule;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    private List<Player> mPlayers;
    private Context mContext;

    public PlayerAdapter(List<Player> mPlayers, Context mContext) {
        this.mPlayers = mPlayers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.player_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.ViewHolder holder, int position) {
        Player player = mPlayers.get(position);
        Glide.with(mContext).load(player.getImage()).into(holder.imgAnh);
        holder.tvTen.setText(player.getTen());
        holder.tvDoi.setText(player.getDoi());
        holder.tvChiTiet.setText(player.getSocial());
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAnh;
        TextView tvTen, tvDoi, tvChiTiet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnh = itemView.findViewById(R.id.img_avatar);
            tvChiTiet = itemView.findViewById(R.id.tv_chitiet);
            tvDoi = itemView.findViewById(R.id.tv_quoc_gia);
            tvTen = itemView.findViewById(R.id.tv_name);
        }
    }
}
