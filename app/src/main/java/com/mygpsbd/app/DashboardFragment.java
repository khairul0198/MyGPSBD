package com.mygpsbd.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindDashboard(view);

        ImageButton btnRefresh = view.findViewById(R.id.btnRefresh);
        if (btnRefresh != null) {
            btnRefresh.setOnClickListener(v -> {
                bindDashboard(view);
                Toast.makeText(requireContext(), "Dashboard updated", Toast.LENGTH_SHORT).show();
            });
        }

        int[] shortcutIds = {
                R.id.btnShortcutMap,
                R.id.btnShortcutVehicles,
                R.id.btnShortcutAlerts,
                R.id.btnShortcutReports,
                R.id.btnShortcutGeofence,
                R.id.btnShortcutPoi,
                R.id.btnShortcutPlayback,
                R.id.btnShortcutSettings
        };

        for (int id : shortcutIds) {
            Button btn = view.findViewById(id);
            if (btn != null) {
                btn.setOnClickListener(v ->
                        Toast.makeText(requireContext(), "Opening " + btn.getText() + "...", Toast.LENGTH_SHORT).show()
                );
            }
        }
    }

    private void bindDashboard(View view) {
        TextView tvUserPhone = view.findViewById(R.id.tvUserPhone);
        TextView tvFleetCount = view.findViewById(R.id.tvFleetCount);
        TextView tvOnlineCount = view.findViewById(R.id.tvOnlineCount);
        TextView tvOfflineCount = view.findViewById(R.id.tvOfflineCount);
        TextView tvDistanceDriven = view.findViewById(R.id.tvDistanceDriven);
        TextView tvDistanceSub = view.findViewById(R.id.tvDistanceSub);
        TextView tvRunningCount = view.findViewById(R.id.tvRunningCount);
        TextView tvIdleCount = view.findViewById(R.id.tvIdleCount);
        TextView tvStoppedCount = view.findViewById(R.id.tvStoppedCount);
        TextView tvCareSummary = view.findViewById(R.id.tvCareSummary);

        if (tvUserPhone != null) {
            Bundle args = getArguments();
            String userCredential = (args != null) ? args.getString("USER_CREDENTIAL") : null;
            if (userCredential != null && !userCredential.trim().isEmpty()) {
                tvUserPhone.setText(userCredential.trim());
            } else {
                tvUserPhone.setText("ফোন বা ইমেইল");
            }
        }

        if (tvFleetCount != null) tvFleetCount.setText("1");
        if (tvOnlineCount != null) tvOnlineCount.setText("0");
        if (tvOfflineCount != null) tvOfflineCount.setText("1");
        if (tvDistanceDriven != null) tvDistanceDriven.setText("0 km");
        if (tvDistanceSub != null) tvDistanceSub.setText("Across 1 vehicle · open reports");
        if (tvRunningCount != null) tvRunningCount.setText("Running: 0");
        if (tvIdleCount != null) tvIdleCount.setText("Idle: 0");
        if (tvStoppedCount != null) tvStoppedCount.setText("Stopped: 0");
        if (tvCareSummary != null) tvCareSummary.setText("Healthy: 0   |   Need Care: 1");
    }
}
