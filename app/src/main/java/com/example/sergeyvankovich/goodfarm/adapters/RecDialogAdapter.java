package com.example.sergeyvankovich.goodfarm.adapters;

import android.bluetooth.BluetoothClass;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergeyvankovich.goodfarm.R;
import com.example.sergeyvankovich.goodfarm.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

class RecDialogAdapter extends RecyclerView.Adapter<RecDialogAdapter.MyHolder>{
    private List<Product> productList;
    private int deviceWidth;

    public RecDialogAdapter(List<Product> productList, int deviceWidth) {
        this.productList = productList;
        this.deviceWidth = deviceWidth;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_card, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.name.setText(productList.get(position).getName());
        holder.num.setText(String.valueOf(productList.get(position).getWeight()) + " ед");
        Picasso.get().load(productList.get(position).getImgUrl()).resize(deviceWidth, deviceWidth).into(holder.image);

    }

    @Override
    public int getItemCount() {
        if (productList == null) return 0;
        return productList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView num;
        ImageView image;

        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            num = itemView.findViewById(R.id.numandweight);
            image = itemView.findViewById(R.id.image);
        }
    }
}
