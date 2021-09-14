package com.example.ptsganjil202111rpl2doni11.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.ptsganjil202111rpl2doni11.R;
import com.example.ptsganjil202111rpl2doni11.RealmHelper;
import com.example.ptsganjil202111rpl2doni11.adapter.DatabaseAdapter;
import com.example.ptsganjil202111rpl2doni11.adapter.TeamAdapter;
import com.example.ptsganjil202111rpl2doni11.model.DatabaseModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class FavoriteMenu extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseAdapter adapter;
    private List<DatabaseModel> arrayList;
    RealmHelper realmHelper;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_menu);

        recyclerView = findViewById(R.id.rv_list);

        realm = Realm.getDefaultInstance();
        realm.init(getApplicationContext());
        realmHelper = new RealmHelper(realm);
        arrayList = new ArrayList<>();

        arrayList = realmHelper.getAllDatabase();
        adapter = new DatabaseAdapter(getApplicationContext(), arrayList, realmHelper, realm);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
        show();
    }

    public void show(){
        adapter = new DatabaseAdapter(this, arrayList, realmHelper, realm);
        recyclerView.setAdapter(adapter);
    }
}