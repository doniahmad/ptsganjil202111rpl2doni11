package com.example.ptsganjil202111rpl2doni11.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptsganjil202111rpl2doni11.R;
import com.example.ptsganjil202111rpl2doni11.RealmHelper;
import com.example.ptsganjil202111rpl2doni11.adapter.DatabaseAdapter;
import com.example.ptsganjil202111rpl2doni11.model.DatabaseModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class FavoriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseAdapter adapter;
    private List<DatabaseModel> arrayList;
    RealmHelper realmHelper;
    Realm realm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_main);
        realm = Realm.getDefaultInstance();
        realm.init(getActivity());
        realmHelper = new RealmHelper(realm);
        arrayList = new ArrayList<>();

        arrayList = realmHelper.getAllDatabase();
        adapter = new DatabaseAdapter(getActivity(), arrayList, realmHelper, realm);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        show();
    }

    public void show() {
        adapter = new DatabaseAdapter(getActivity(), arrayList, realmHelper, realm);
        recyclerView.setAdapter(adapter);
    }
}
