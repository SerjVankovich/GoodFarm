package com.example.sergeyvankovich.goodfarm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sergeyvankovich.goodfarm.R;
import com.example.sergeyvankovich.goodfarm.db.DBSingleton;
import com.example.sergeyvankovich.goodfarm.entities.SetEntity;
import com.example.sergeyvankovich.goodfarm.models.Set;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyHolder> {
    private List<Set> sets;

    private Context context;
    private int deviceWidth;

    public RecAdapter(List<Set> sets, Context context, int deviceWidth) {
        this.sets = sets;
        this.context = context;
        this.deviceWidth = deviceWidth;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_view, parent, false);

        final MyHolder holder = new MyHolder(view);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builtDialog(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.price.setText(String.valueOf(sets.get(position).getPrice()) + " руб.");
        holder.description.setText(sets.get(position).getDescription());
        holder.name.setText(sets.get(position).getName());
        setImg(holder, position);



    }

    private void setImg(MyHolder holder, int position) {
        if (sets.get(position).getName().equals("СЕМЕЙНЫЙ НАБОР")) {
            Picasso.get().load(R.drawable.family_nabor).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        if (sets == null) return 0;
        return sets.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView name;
        RoundedImageView image;
        TextView description;
        TextView price;
        LinearLayout cardView;

        public MyHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.set_img);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

    private void builtDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.gialog_recycler, null);

        Button addToCart = view.findViewById(R.id.add_to_cart);
        Button cancell = view.findViewById(R.id.cancell);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);


        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new RecDialogAdapter(sets.get(position).getProductList(), deviceWidth));

        builder.setView(view);
        final AlertDialog dialog = builder.create();

        cancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertToDb(sets.get(position));
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }

    @SuppressLint("CheckResult")
    private void insertToDb(Set set) {
        Completable.fromAction(() -> DBSingleton.getInstance(context).getAppDatabase().setDAO().insert(new SetEntity(set.getName(), set.getDescription(),
                set.getPrice(), set.getImgUrl())))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe();
    }
}
