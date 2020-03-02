package com.nguyenhuong.worldcup.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhuong.worldcup.R;
import com.nguyenhuong.worldcup.adapter.PlayerAdapter;
import com.nguyenhuong.worldcup.adapter.ScheduleAdapter;
import com.nguyenhuong.worldcup.modal.Player;
import com.nguyenhuong.worldcup.modal.Schedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class PlayerFragment extends Fragment {
    private static PlayerFragment INSTANCE;
    private RecyclerView recyclerView;
    private PlayerAdapter mPlayerAdapter;
    private List<Player> mPlayers;

    private PlayerFragment() {
    }

    public static PlayerFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_fragment_layout, container, false);
        recyclerView = view.findViewById(R.id.rv_player);
        mPlayers = new ArrayList<>();
        mPlayerAdapter = new PlayerAdapter(mPlayers, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mPlayerAdapter);
        new DownloadImage().execute("https://obscure-gorge-93598.herokuapp.com/cauthu/all");
        return view;


    }


    class DownloadImage extends AsyncTask<String, Void, List<Player>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Player> doInBackground(String... args) {
            String link = args[0];
            try {
                URL url = new URL(link);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line != null) {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                List<Player> players = parseJson("{\"cauthu\" :" + builder.toString() + "}");
                return players;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        private List<Player> parseJson(String json) {
            List<Player> players = new ArrayList<>();
            try {
                JSONObject root = new JSONObject(json);
                JSONArray array = root.getJSONArray("cauthu");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject play = array.getJSONObject(i);
                    int id = play.getInt("id");
                    String ten = play.getString("ten");
                    String doi = play.getString("doi");
                    String image = play.getString("image");
                    String social = play.getString("social");
                    Player player = new Player(ten, doi, image, social);
                    players.add(player);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return players;
        }

        @Override
        protected void onPostExecute(List<Player> players) {
            super.onPostExecute(players);
            mPlayers.clear();
            mPlayers.addAll(players);
            mPlayerAdapter.notifyDataSetChanged();
        }
    }

}
