package com.nguyenhuong.worldcup.fragment;


import android.graphics.Bitmap;
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

import com.nguyenhuong.worldcup.MainActivity;
import com.nguyenhuong.worldcup.R;
import com.nguyenhuong.worldcup.adapter.ScheduleAdapter;
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

public class ScheduleFragment extends Fragment {
    private static ScheduleFragment INSTANCE;
    private RecyclerView mRecyclerView;
    private ScheduleAdapter mScheduleAdapter;
    private List<Schedule> mSchedules;

    private ScheduleFragment() {
    }

    public static ScheduleFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScheduleFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.schedule_fragment_layout, container, false);
        mRecyclerView = view.findViewById(R.id.rv_schedule);
        mSchedules = new ArrayList<>();
        mScheduleAdapter = new ScheduleAdapter(mSchedules, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mScheduleAdapter);
        new DownloadImage().execute("https://obscure-gorge-93598.herokuapp.com/lichthidau/all");
        return view;


    }


    class DownloadImage extends AsyncTask<String, Void, List<Schedule>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Schedule> doInBackground(String... args) {
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
                List<Schedule> mSchedules = parseJson("{\"lich\" :" + builder.toString() + "}");
                return mSchedules;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        private List<Schedule> parseJson(String json) {
            List<Schedule> schedules = new ArrayList<>();
            try {
                JSONObject root = new JSONObject(json);
                JSONArray array = root.getJSONArray("lich");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject schedule = array.getJSONObject(i);
                    int id = schedule.getInt("id");
                    String doi1 = schedule.getString("doi1");
                    String quocky1 = schedule.getString("quocky1");
                    String doi2 = schedule.getString("doi2");
                    String quocky2 = schedule.getString("quocky2");
                    String ngay = schedule.getString("ngay");
                    String gio = schedule.getString("gio");
                    String kenh = schedule.getString("kenh");
                    String vong = schedule.getString("vong");
                    Schedule sc = new Schedule(id, doi1, quocky1, doi2, quocky2, ngay, gio, vong, kenh);
                    schedules.add(sc);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return schedules;
        }

        @Override
        protected void onPostExecute(List<Schedule> schedules) {
            super.onPostExecute(schedules);
            mSchedules.clear();
            mSchedules.addAll(schedules);
            mScheduleAdapter.notifyDataSetChanged();
        }
    }
}
