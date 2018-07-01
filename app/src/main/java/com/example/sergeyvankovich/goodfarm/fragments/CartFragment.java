package com.example.sergeyvankovich.goodfarm.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergeyvankovich.goodfarm.MainActivity;
import com.example.sergeyvankovich.goodfarm.R;
import com.example.sergeyvankovich.goodfarm.adapters.RecAdapter;
import com.example.sergeyvankovich.goodfarm.db.DBSingleton;
import com.example.sergeyvankovich.goodfarm.entities.SetEntity;
import com.example.sergeyvankovich.goodfarm.models.Set;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class CartFragment extends Fragment {
    List<Set> sets;
    RecyclerView recyclerView;

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final int width = MainActivity.getWindowWidth();

        DBSingleton.getInstance(getContext()).getAppDatabase().setDAO().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(setEntities -> {
                    sets = fromSetEntityToSet(setEntities);
                    RecAdapter adapter = new RecAdapter(sets, getContext(), width);
                    recyclerView.setAdapter(adapter);

                });

        return view;
    }

    private List<Set> fromSetEntityToSet (List<SetEntity> list) {
        List<Set> array = new ArrayList<>();

        for (SetEntity entity:
             list) {
            Set set = new Set(entity.name, entity.description, entity.price, entity.imgUrl);
            array.add(set);
        }

        return array;
    }
}
