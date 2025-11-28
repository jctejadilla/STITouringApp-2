package com.example.stitouringapp.ui.interactivemap;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.webkit.WebViewAssetLoader;

import com.example.stitouringapp.R;
import com.example.stitouringapp.databinding.FragmentInteractiveMapBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class InteractiveMapFragment extends Fragment {

    private FragmentInteractiveMapBinding binding;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInteractiveMapBinding.inflate(inflater, container, false);
        mAuth = FirebaseAuth.getInstance();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String mapId = InteractiveMapFragmentArgs.fromBundle(getArguments()).getMapId();

        switch (mapId) {
            case "lobby_map":
                binding.interactiveMapImage.setImageResource(R.drawable.lobby);
                setupLobbyNodes();
                break;
            case "mezzanine_map":
                binding.interactiveMapImage.setImageResource(R.drawable.mezzanine);
                setupMezzanineNodes();
                break;
            case "canteen_map":
                binding.interactiveMapImage.setImageResource(R.drawable.canteencourt);
                setupCanteenAndCourtNodes();
                break;
            case "second_floor_map":
                binding.interactiveMapImage.setImageResource(R.drawable.secondfloor);
                setupSecondFloorNodes();
                break;
            case "third_floor_map":
                binding.interactiveMapImage.setImageResource(R.drawable.thirdfloor);
                setupThirdFloorNodes();
                break;
            case "fourth_floor_map":
                binding.interactiveMapImage.setImageResource(R.drawable.fourthfloor);
                setupFourthFloorNodes();
                break;
            case "annex_ground_floor_map":
                binding.interactiveMapImage.setImageResource(R.drawable.annex_groundfloor);
                setupAnnexGroundFloorNodes();
                break;
            case "annex_second_floor_map":
                binding.interactiveMapImage.setImageResource(R.drawable.annex_secondfloor);
                setupAnnexSecondFloorNodes();
                break;
            case "annex_first_floor_map":
                binding.interactiveMapImage.setImageResource(R.drawable.annex_firstfloor);
                setupAnnexFirstFloorNodes();
                break;
            case "annex_third_floor_map":
                binding.interactiveMapImage.setImageResource(R.drawable.annex_thirdfloor);
                setupAnnexThirdFloorNodes();
                break;
            case "annex_auditorium_map":
                binding.interactiveMapImage.setImageResource(R.drawable.annex_auditorium);
                setupAnnexAuditoriumNodes();
                break;
            default:
                // Handle default case or show an error
                break;
        }

        binding.backButton.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(InteractiveMapFragment.this);
            navController.popBackStack();
        });

        binding.backButton.bringToFront();
    }

    private void setupLobbyNodes() {
        // Existing Purple Nodes for Hallways (UNCHANGED)
        setupNode(binding.node1, 0.55f, 0.120f, "Lobby");
        setupNode(binding.node2, 0.5f, 0.6f, "Ground Floor Hallway");
        setupNode(binding.node3, 0.5f, 0.95f, "Canteen");

        // Blue Nodes for Rooms (Corrected Positions)
        setupNode(binding.node5, 0.4f, 0.125f, "Admission");
        setupNode(binding.node6, 0.525f, 0.02f, "Clinic");
        setupNode(binding.node7, 0.57f, 0.55f, "Registrar");
        setupNode(binding.node8, 0.4f, 0.63f, "COMLAB1");
        setupNode(binding.node9, 0.4f, 0.80f, "COMLAB2");
        setupNode(binding.node10, 0.4f, 0.95f, "COMLAB3");
        setupNode(binding.node11, 0.57f, 0.74f, "COMLAB4");
    }
    private void setupCanteenAndCourtNodes() {
        // NO BLUE NODES FOR THIS MAP
        setupNode(binding.node1, 0.52f, 0.10f, "Canteen Hallway");
        setupNode(binding.node2, 0.52f, 0.47f, "Canteen Inside");
        setupNode(binding.node3, 0.52f, 0.85f, "Court");
    }
    private void setupMezzanineNodes() {
        // Purple hallway nodes
        setupNode(binding.node1, 0.52f, 0.3f, "Mezzanine Hallway");
        setupNode(binding.node2, 0.52f, 0.75f, "Mezzanine Middle Hallway");

        // Blue room nodes
        setupNode(binding.node5, 0.4f, 0.20f, "Teacher's Room");
        setupNode(binding.node6, 0.57f, 0.50f, "IT Department");
        setupNode(binding.node7, 0.4f, 0.60f, "BMMA LAB");
        setupNode(binding.node8, 0.4f, 0.77f, "Library (Books)");
    }

    private void setupSecondFloorNodes() {
        // Purple hallway nodes
        setupNode(binding.node1, 0.5f, 0.1f, "2nd Floor Hallway");
        setupNode(binding.node2, 0.5f, 0.55f, "2nd Floor Hallway");
        setupNode(binding.node3, 0.5f, 0.90f, "2nd Floor Hallway");

        // Blue room nodes
        setupNode(binding.node5, 0.35f, 0.3f, "Room 206");
        setupNode(binding.node6, 0.35f, 0.58f, "Room 205");
        setupNode(binding.node7, 0.35f, 0.89f, "Room 204");
        setupNode(binding.node8, 0.65f, 0.115f, "Room 201");
        setupNode(binding.node9, 0.65f, 0.4f, "Room 202");
        setupNode(binding.node10, 0.65f, 0.65f, "Room 203");
    }

    private void setupThirdFloorNodes() {
        // Purple hallway nodes
        setupNode(binding.node1, 0.51f, 0.115f, "3rd Floor Hallway 1");
        setupNode(binding.node2, 0.51f, 0.55f, "3rd Floor Hallway 2");
        setupNode(binding.node3, 0.51f, 0.90f, "3rd Floor Hallway 3");

        // Blue room nodes
        setupNode(binding.node5, 0.35f, 0.3f, "Room 301");
        setupNode(binding.node6, 0.35f, 0.6f, "Room 302");
        setupNode(binding.node7, 0.35f, 0.88f, "Pastry Laboratory");
        setupNode(binding.node8, 0.65f, 0.115f, "Room 303");
        setupNode(binding.node9, 0.65f, 0.5f, "Kitchen Laboratory");
    }

    private void setupFourthFloorNodes() {
        // Purple hallway nodes
        setupNode(binding.node1, 0.5f, 0.115f, "4th Floor Hallway 1");
        setupNode(binding.node2, 0.5f, 0.5f, "4th Floor Hallway 2");
        setupNode(binding.node3, 0.5f, 0.9f, "4th Floor Hallway 3");

        // Blue room nodes
        setupNode(binding.node5, 0.35f, 0.3f, "Room 406");
        setupNode(binding.node6, 0.35f, 0.55f, "Room 405");
        setupNode(binding.node7, 0.35f, 0.80f, "Room 404");
        setupNode(binding.node9, 0.65f, 0.105f, "Room 401");
        setupNode(binding.node10, 0.65f, 0.38f, "Room 402");
        setupNode(binding.node11, 0.65f, 0.67f, "Room 403");
    }

    private void setupAnnexGroundFloorNodes() {
        // Purple Nodes
        setupNode(binding.node1, 0.65f, 0.2f, "Annex Ground Stairs");
        setupNode(binding.node2, 0.5f, 0.5f, "Annex Ground Hallway");

        // Blue Nodes
        setupNode(binding.node6, 0.4f, 0.95f, "GF101");
    }

    private void setupAnnexFirstFloorNodes() {
        // Purple Nodes
        setupNode(binding.node1, 0.75f, 0.3f, "Annex Second Stairs");
        setupNode(binding.node2, 0.5f, 0.5f, "Annex Second Hallway");
        setupNode(binding.node3, 0.25f, 0.8f, "Annex Second Stairs");

        // Blue Nodes
        setupNode(binding.node5, 0.53f, 0.15f, "Annex Room 103");
        setupNode(binding.node6, 0.45f, 0.85f, "Annex Room 102");
        setupNode(binding.node7, 0.67f, 0.85f, "Annex Room 101");
    }

    private void setupAnnexSecondFloorNodes() {
        // Purple Nodes
        setupNode(binding.node1, 0.75f, 0.3f, "Annex Second Stairs");
        setupNode(binding.node2, 0.5f, 0.5f, "Annex Second Hallway");
        setupNode(binding.node3, 0.25f, 0.8f, "Annex Second Stairs");

        // Blue Nodes
        setupNode(binding.node5, 0.53f, 0.15f, "Annex Room 203");
        setupNode(binding.node6, 0.45f, 0.85f, "Annex Room 202");
        setupNode(binding.node7, 0.67f, 0.85f, "Annex Room 201");
    }

    private void setupAnnexThirdFloorNodes() {
        // Purple Nodes
        setupNode(binding.node1, 0.75f, 0.3f, "Annex Second Stairs");
        setupNode(binding.node2, 0.5f, 0.5f, "Annex Second Hallway");
        setupNode(binding.node3, 0.25f, 0.8f, "Annex Second Stairs");

        // Blue Nodes
        setupNode(binding.node5, 0.53f, 0.15f, "Annex Room 303");
        setupNode(binding.node6, 0.45f, 0.85f, "Annex Room 302");
        setupNode(binding.node7, 0.67f, 0.85f, "Annex Room 301");
    }

    private void setupAnnexAuditoriumNodes() {
        // Purple Nodes
        setupNode(binding.node1, 0.77f, 0.5f, "Auditorium Hallway");
        setupNode(binding.node2, 0.26f, 0.5f, "Auditorium Hallway");

        // Blue Node (Exception)
        setupNode(binding.node5, 0.55f, 0.5f, "Auditorium");
    }

    private void setupNode(ImageView node, float biasTop, float biasStart, String locationName) {
        node.setVisibility(View.VISIBLE);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) node.getLayoutParams();
        params.topToTop = R.id.interactive_map_image;
        params.startToStart = R.id.interactive_map_image;
        params.bottomToBottom = R.id.interactive_map_image;
        params.endToEnd = R.id.interactive_map_image;
        params.verticalBias = biasTop;
        params.horizontalBias = biasStart;
        node.setLayoutParams(params);

        node.setOnClickListener(v -> {
            switch (locationName) {
                // Building 1 Panoramas
                case "Lobby":
                    showPanoramaDialog("lobby.html");
                    break;
                case "Ground Floor Hallway":
                    showPanoramaDialog("groundfhall.html");
                    break;
                case "Canteen":
                case "Canteen Hallway":
                    showPanoramaDialog("canteen.html");
                    break;
                case "Court":
                    showPanoramaDialog("court.html");
                    break;
                case "Canteen Inside":
                    showPanoramaDialog("canteenin.html");
                    break;
                case "Mezzanine Middle Hallway":
                    showPanoramaDialog("mezzhall.html");
                    break;
                case "Mezzanine Hallway":
                    showPanoramaDialog("mezz.html");
                    break;
                case "3rd Floor Hallway 1":
                    showPanoramaDialog("3rd1.html");
                    break;
                case "3rd Floor Hallway 2":
                    showPanoramaDialog("3rd2.html");
                    break;
                case "3rd Floor Hallway 3":
                    showPanoramaDialog("3rd3.html");
                    break;
                case "4th Floor Hallway 1":
                    showPanoramaDialog("4th1.html");
                    break;
                case "4th Floor Hallway 2":
                    showPanoramaDialog("4th2.html");
                    break;
                case "4th Floor Hallway 3":
                    showPanoramaDialog("4th3.html");
                    break;

                // Annex Building Panoramas
                case "Annex Ground Stairs":
                case "Annex Second Stairs":
                case "Annex Third Stairs":
                    showPanoramaDialog("annex_stairs.html"); // Assuming one html for all annex stairs
                    break;
                case "Annex Ground Hallway":
                    showPanoramaDialog("annex_g_hall.html");
                    break;
                case "Annex Second Hallway":
                    showPanoramaDialog("annex_2_hall.html");
                    break;
                case "Annex Third Hallway":
                    showPanoramaDialog("annex_3_hall.html");
                    break;
                case "Auditorium Hallway":
                    showPanoramaDialog("annex_audi_hall.html");
                    break;

                default:
                    // This will handle all numbered rooms and the Auditorium
                    showScheduleDialog(locationName);
                    break;
            }
        });

        node.bringToFront();
    }

    private void showPanoramaDialog(String htmlFileName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_webview, null);
        WebView panoramaWebView = dialogView.findViewById(R.id.webView);

        final WebViewAssetLoader assetLoader = new WebViewAssetLoader.Builder()
                .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(requireContext()))
                .build();

        panoramaWebView.setWebViewClient(new LocalContentWebViewClient(assetLoader));

        panoramaWebView.getSettings().setJavaScriptEnabled(true);
        panoramaWebView.getSettings().setAllowFileAccess(false);
        panoramaWebView.getSettings().setAllowContentAccess(false);

        panoramaWebView.loadUrl("https://appassets.androidplatform.net/assets/" + htmlFileName);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();

        if (dialog.getWindow() != null) {
            android.util.DisplayMetrics displayMetrics = new android.util.DisplayMetrics();
            requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int dialogHeight = displayMetrics.heightPixels / 3;
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight);
        }
    }

    private static class LocalContentWebViewClient extends androidx.webkit.WebViewClientCompat {

        private final WebViewAssetLoader mAssetLoader;

        LocalContentWebViewClient(WebViewAssetLoader assetLoader) {
            mAssetLoader = assetLoader;
        }

        @Override
        @Nullable
        public android.webkit.WebResourceResponse shouldInterceptRequest(
                android.webkit.WebView view,
                android.webkit.WebResourceRequest request) {
            return mAssetLoader.shouldInterceptRequest(request.getUrl());
        }
    }

    private void showScheduleDialog(String roomName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Schedule for " + roomName);
        builder.setMessage(getScheduleForRoom(roomName));
        builder.setPositiveButton("Close", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private String getScheduleForRoom(String roomName) {

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null && currentUser.isAnonymous()) {
            return "Guest users cannot view schedules. Please create an account to see room schedules.";
        }

        try {
            InputStream is = requireContext().getAssets().open("schedule.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            int bytesRead = is.read(buffer);
            is.close();

            String json = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
            JSONObject schedules = new JSONObject(json);

            String jsonKey = roomName;
            if (!schedules.has(jsonKey)) {
                String alternateKey = roomName.replace(" ", "").toUpperCase();
                if (schedules.has(alternateKey)) {
                    jsonKey = alternateKey;
                } else if (roomName.equals("Library (Books)")) {
                    jsonKey = "Library";
                }
            }

            if (schedules.has(jsonKey)) {
                JSONObject roomSchedule = schedules.getJSONObject(jsonKey);
                StringBuilder scheduleText = new StringBuilder();
                String[] daysOrder = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

                for (String day : daysOrder) {
                    if (roomSchedule.has(day)) {
                        scheduleText.append(day).append(":\n");
                        scheduleText.append(roomSchedule.getString(day)).append("\n\n");
                    }
                }
                return scheduleText.toString().trim();
            } else {
                return "No schedule available for this room.";
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return "Error loading schedule data.";
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
