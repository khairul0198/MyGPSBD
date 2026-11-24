package com.mygpsbd.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

data class DashboardSummary(
    val phone: String = "01601011171",
    val fleetCount: Int = 1,
    val onlineCount: Int = 0,
    val offlineCount: Int = 1,
    val distanceKm: Double = 0.0,
    val running: Int = 0,
    val idle: Int = 0,
    val stopped: Int = 0,
    val healthy: Int = 0,
    val needCare: Int = 1
)

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sampleData = DashboardSummary()
        bindDashboard(view, sampleData)

        view.findViewById<ImageButton>(R.id.btnRefresh).setOnClickListener {
            bindDashboard(view, sampleData)
            Toast.makeText(requireContext(), "Dashboard updated", Toast.LENGTH_SHORT).show()
        }

        val shortcutMap = mapOf(
            R.id.btnShortcutMap to "Map",
            R.id.btnShortcutVehicles to "Vehicles",
            R.id.btnShortcutAlerts to "Alerts",
            R.id.btnShortcutReports to "Reports",
            R.id.btnShortcutGeofence to "Geofence",
            R.id.btnShortcutPoi to "POI",
            R.id.btnShortcutPlayback to "Playback",
            R.id.btnShortcutSettings to "Settings"
        )

        shortcutMap.forEach { (id, label) ->
            view.findViewById<Button>(id).setOnClickListener {
                Toast.makeText(requireContext(), "Opening $label...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bindDashboard(view: View, data: DashboardSummary) {
        view.findViewById<TextView>(R.id.tvUserPhone).text = data.phone
        view.findViewById<TextView>(R.id.tvFleetCount).text = data.fleetCount.toString()
        view.findViewById<TextView>(R.id.tvOnlineCount).text = data.onlineCount.toString()
        view.findViewById<TextView>(R.id.tvOfflineCount).text = data.offlineCount.toString()
        view.findViewById<TextView>(R.id.tvDistanceDriven).text = "${data.distanceKm.toInt()} km"
        view.findViewById<TextView>(R.id.tvDistanceSub).text =
            "Across ${data.fleetCount} vehicle · open reports"
        view.findViewById<TextView>(R.id.tvRunningCount).text = "Running: ${data.running}"
        view.findViewById<TextView>(R.id.tvIdleCount).text = "Idle: ${data.idle}"
        view.findViewById<TextView>(R.id.tvStoppedCount).text = "Stopped: ${data.stopped}"
        view.findViewById<TextView>(R.id.tvCareSummary).text =
            "Healthy: ${data.healthy}   |   Need Care: ${data.needCare}"
    }
}
