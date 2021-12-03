package com.example.tubespw_mehtravelling.listDestinasi;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tubespw_mehtravelling.R;

import com.example.tubespw_mehtravelling.databinding.ActivityRecyclerViewAdapterBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {

    private Context context;
    private List<DataDestinasi> listdestinasi;
    LinearLayout tampildatadestinasi;

    public RecyclerViewAdapter(Context context, List<DataDestinasi> listdestinasi) {
        this.context = context;
        this.listdestinasi = listdestinasi;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ActivityRecyclerViewAdapterBinding binding = ActivityRecyclerViewAdapterBinding.inflate(layoutInflater, parent, false);
        return new viewHolder(binding);
    }

    //    @Override
//    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
//
//        Glide.with(holder.binding.getRoot())
//                .load(listdestinasi.get(position).getImgurl())
//
//                .into(holder.binding.ProfilePicture);
//
//        holder.binding.tvNamadestinasi.setText(listdestinasi.get(position).getNamaDestinasi());
//        holder.binding.tvAlamatdestinasi.setText(listdestinasi.get(position).getAlamatDestinasi());
//        holder.binding.tvDeskripsidestinasi.setText(listdestinasi.get(position).getAlamatDestinasi());
//    }
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        final DataDestinasi dst = listdestinasi.get(position);
        holder.bind(dst);
//        holder.binding.tampildatadestinasi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                builder.setTitle("Data Destinasi");
//                builder.setMessage("Destinasi yang dipilih adalah "+ holder.binding.tvNamadestinasi.getText().toString());
//                builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//                    }
//                }) .show();
//            }
//        }
//        );
    }



    //    @Override
//    public int getItemCount() {
//        return listdestinasi.size();
//    }
//
//    public class viewHolder extends RecyclerView.ViewHolder {
//        private ActivityRecyclerViewAdapterBinding binding;
//
//        public viewHolder(@NonNull ActivityRecyclerViewAdapterBinding binding) {
//            super(binding.getRoot());
//            this.binding = binding;
//        }
//    }
    @Override
    public int getItemCount() {
        return listdestinasi.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ActivityRecyclerViewAdapterBinding binding;

        public viewHolder(@NonNull ActivityRecyclerViewAdapterBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(DataDestinasi Destinasi) {
            binding.setDst(Destinasi);
            binding.setImgUrl(Destinasi.imgURL);
            binding.executePendingBindings();
        }


        @Override
        public void onClick(View view) {

        }
    }
}