package com.allam.carsapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.allam.carsapp.databinding.ItemCarBinding;
import com.allam.carsapp.db.models.CarData;
import com.allam.utils.MyUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.mVH> {
    private  List<CarData> list = new ArrayList<>();
    private  Context context;

    public CarsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public mVH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemCarBinding binding = ItemCarBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new mVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull mVH holder, int position) {
        CarData item = list.get(position);
        MyUtils.m_Picasso(context, item.getImageUrl(), holder.binding.profileImg);
        holder.binding.carBrand.setText(item.getBrand());
        if (item.getIsUsed()) {
            holder.binding.carUsage.setText("is Usage");
        } else {
            holder.binding.carUsage.setText("New");
        }
        holder.binding.constructionYear.setText(item.getConstractionYear());

    }

    @Override
    public int getItemCount() {
        if (list.size() != 0) {
            return list.size();
        } else {
            return 0;
        }
    }
    public void setList(List<CarData> list) {
        if (this.list.isEmpty()) {
            this.list = list;
        } else {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public class mVH extends RecyclerView.ViewHolder {
        ItemCarBinding binding;

        public mVH(@NonNull @NotNull ItemCarBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
