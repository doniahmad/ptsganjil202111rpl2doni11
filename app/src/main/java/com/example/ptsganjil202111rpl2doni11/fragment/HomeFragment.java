package com.example.ptsganjil202111rpl2doni11.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.ptsganjil202111rpl2doni11.R;
import com.example.ptsganjil202111rpl2doni11.RealmHelper;
import com.example.ptsganjil202111rpl2doni11.adapter.TeamAdapter;
import com.example.ptsganjil202111rpl2doni11.model.TeamModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;

public class HomeFragment extends Fragment {

    private String BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League";
    private RecyclerView recyclerView;
    private TeamAdapter adapter;
    private ArrayList<TeamModel> arrayList;
    Realm realm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_main);
        recyclerView.setHasFixedSize(true);
        realm = Realm.getDefaultInstance();
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
                            adapter = new TeamAdapter(getActivity(), arrayList);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(layoutManager);
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
