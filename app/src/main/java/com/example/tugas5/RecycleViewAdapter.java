package com.example.tugas5;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MahasiswaViewHolder>
implements Filterable {
    private Context context;
    private List<Mahasiswa> mahasiswaList;
    private List<Mahasiswa> mahasiswaListAll;

    public RecycleViewAdapter(Context context, ArrayList<Mahasiswa> mahasiswaList){
        this.context = context;
        this.mahasiswaList = mahasiswaList;
        this.mahasiswaListAll = new ArrayList<>(mahasiswaList);
    }

    class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        TextView _txName, _txNim, _txMajor;
        ImageView _photo;

        MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            _txName = itemView.findViewById(R.id.tv_name);
            _txNim = itemView.findViewById(R.id.tv_nim);
            _txMajor = itemView.findViewById(R.id.tv_major);
            _photo = itemView.findViewById(R.id.img_avatar);
        }
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        final Mahasiswa result = mahasiswaList.get(position);
        holder._txName.setText(result.get_name());
        holder._txNim.setText(result.get_nim());
        holder._txMajor.setText(result.get_major());
        Glide.with(context)
                .load(result.get_photo())
                .fitCenter()
                .dontAnimate()
                .dontTransform()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder._photo);
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    // Filter
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<Mahasiswa> filteredList = new ArrayList<>();
                CharSequence in = charSequence.toString().toLowerCase();

                if (charSequence.toString().isEmpty()){
                    filteredList.addAll(mahasiswaListAll);
                } else {
                    for (Mahasiswa item: mahasiswaListAll){
                        if (item.get_name().toLowerCase().contains(in) ||
                                item.get_nim().toLowerCase().contains(in) ||
                                item.get_major().toLowerCase().contains(in)){
                            filteredList.add(item);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                mahasiswaList.clear();
                mahasiswaList.addAll((Collection<? extends Mahasiswa>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
}

