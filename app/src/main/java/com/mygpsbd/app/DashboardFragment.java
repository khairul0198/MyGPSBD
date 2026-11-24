package com.example.yourapp; // তোমার প্যাকেজ নেম বসাবে

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {

    private TextView tvUserCredential, tvFleetCount, tvOnlineCount, tvOfflineCount, tvDistanceKm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        tvUserCredential = view.findViewById(R.id.tvUserCredential);
        tvFleetCount = view.findViewById(R.id.tvFleetCount);
        tvOnlineCount = view.findViewById(R.id.tvOnlineCount);
        tvOfflineCount = view.findViewById(R.id.tvOfflineCount);
        tvDistanceKm = view.findViewById(R.id.tvDistanceKm);

        // Bundle থেকে লগইন করা ইউজারের ফোন/ইমেইল নেওয়া
        if (getArguments() != null && getArguments().containsKey("USER_CREDENTIAL")) {
            String userCred = getArguments().getString("USER_CREDENTIAL");
            if (userCred != null && !userCred.isEmpty()) {
                tvUserCredential.setText(userCred);
            }
        }

        // ডামি ডাটা সেট করা
        loadDummyData();

        // শর্টকাট বাটনগুলোর ক্লিক লিসেনার
        setupClickListeners(view);

        return view;
    }

    private void loadDummyData() {
        tvFleetCount.setText("1");
        tvOnlineCount.setText("• 0");
        tvOfflineCount.setText("1");
        tvDistanceKm.setText("0");
    }

    private void setupClickListeners(View view) {
        view.findViewById(R.id.btnShortcutMap).setOnClickListener(v -> showMsg("Map পেজ ওপেন হচ্ছে"));
        view.findViewById(R.id.btnShortcutVehicles).setOnClickListener(v -> showMsg("Vehicles পেজ ওপেন হচ্ছে"));
        view.findViewById(R.id.btnShortcutAlerts).setOnClickListener(v -> showMsg("Alerts পেজ ওপেন হচ্ছে"));
        view.findViewById(R.id.btnShortcutReports).setOnClickListener(v -> showMsg("Reports পেজ ওপেন হচ্ছে"));
        view.findViewById(R.id.btnShortcutGeofence).setOnClickListener(v -> showMsg("Geofence পেজ ওপেন হচ্ছে"));
        view.findViewById(R.id.btnShortcutPoi).setOnClickListener(v -> showMsg("POI পেজ ওপেন হচ্ছে"));
        view.findViewById(R.id.btnShortcutPlayback).setOnClickListener(v -> showMsg("Playback পেজ ওপেন হচ্ছে"));
        view.findViewById(R.id.btnShortcutSettings).setOnClickListener(v -> showMsg("Settings পেজ ওপেন হচ্ছে"));
        view.findViewById(R.id.btnViewAllFleet).setOnClickListener(v -> showMsg("Vehicles লিস্ট ওপেন হচ্ছে"));
        view.findViewById(R.id.btnRefresh).setOnClickListener(v -> showMsg("ডাটা রিফ্রেশ হয়েছে"));
    }

    private void showMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
