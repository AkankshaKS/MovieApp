package com.e.bestmoviesapp.main.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.e.bestmoviesapp.R;
import com.e.bestmoviesapp.databinding.ItemCastBinding;
import com.e.bestmoviesapp.main.model.Cast;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    private List<Cast> castList;
    private final int limit = 5;


    public CastAdapter(List<Cast> casts) {
        castList = casts;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCastBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_cast,parent,false);
        return new CastViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        Cast cast = castList.get(position);
        holder.bind(cast);
    }

    @Override
    public int getItemCount() {
        if(castList.size() > limit){
            return limit;
        }
        else {
            return castList.size();
        }
    }

    public void addAll(List<Cast> casts) {
        castList.clear();
        castList.addAll(casts);
        notifyDataSetChanged();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder {

        ItemCastBinding binding;

        CastViewHolder(ItemCastBinding itemCastBinding) {
            super(itemCastBinding.getRoot());
            binding = itemCastBinding;
        }

        void bind(final Cast cast) {
                binding.textCastName.setText(cast.getName());
                binding.setCast(cast);





        }
    }
}
