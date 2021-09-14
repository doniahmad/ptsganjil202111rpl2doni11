package com.example.ptsganjil202111rpl2doni11.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.ptsganjil202111rpl2doni11.R;
import com.example.ptsganjil202111rpl2doni11.adapter.DatabaseAdapter;
import com.example.ptsganjil202111rpl2doni11.adapter.TeamAdapter;
import com.example.ptsganjil202111rpl2doni11.model.TeamModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class ListMenu extends AppCompatActivity {

    private String BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League";
    private RecyclerView recyclerView;
    private TeamAdapter adapter;
    private ArrayList<TeamModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);

        recyclerView = findViewById(R.id.rv_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        addData();
    }

    private void addData() {
        arrayList = new ArrayList<>();

        AndroidNetworking.get(BASE_URL)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            arrayList = new ArrayList<>();
                            JSONArray teamsArray = response.getJSONArray("teams");
                            for (int i = 0; i < teamsArray.length(); i++) {
                                JSONObject teamObject = teamsArray.getJSONObject(i);
                                String name = teamObject.getString("strTeam");
                                String stadium = teamObject.getString("strStadiumThumb");
                                String desc = teamObject.getString("strDescriptionEN");
                                String country = teamObject.getString("strCountry");
                                String badge = teamObject.getString("strTeamBadge");
                                arrayList.add(new TeamModel(name,stadium,desc,country,badge));
                            }
                            adapter = new TeamAdapter(getApplicationContext(), arrayList);
                            recyclerView.setAdapter(adapter);
                        } catch (Exception e) {
                            Log.d("error", e.toString());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("error", anError.toString());
                    }
                });
    }
}