package com.example.stitouringapp.ui.interactivemap;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.stitouringapp.R;
import com.example.stitouringapp.databinding.FragmentInteractiveMapBinding;
import com.example.stitouringapp.ui.interactivemap.InteractiveMapFragmentArgs;

public class InteractiveMapFragment extends Fragment {

    private FragmentInteractiveMapBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInteractiveMapBinding.inflate(inflater, container, false);
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
        setupNode(binding.node2, 0.52f, 0.47f, "Canteen Hallway");
        setupNode(binding.node3, 0.52f, 0.85f, "Court");
    }
    private void setupMezzanineNodes() {
        // Purple hallway nodes
        setupNode(binding.node1, 0.52f, 0.3f, "Mezzanine Hallway");
        setupNode(binding.node2, 0.52f, 0.75f, "Mezzanine Hallway");

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
        setupNode(binding.node1, 0.51f, 0.115f, "3rd Floor Hallway");
        setupNode(binding.node2, 0.51f, 0.55f, "3rd Floor Hallway");
        setupNode(binding.node3, 0.51f, 0.90f, "3rd Floor Hallway");

        // Blue room nodes
        setupNode(binding.node5, 0.35f, 0.3f, "Room 301");
        setupNode(binding.node6, 0.35f, 0.6f, "Room 302");
        setupNode(binding.node7, 0.35f, 0.88f, "Pastry Laboratory");
        setupNode(binding.node8, 0.65f, 0.115f, "Room 303");
        setupNode(binding.node9, 0.65f, 0.5f, "Kitchen Laboratory");
    }

    private void setupFourthFloorNodes() {
        // Purple hallway nodes
        setupNode(binding.node1, 0.5f, 0.115f, "4th Floor Hallway");
        setupNode(binding.node2, 0.5f, 0.5f, "4th Floor Hallway");
        setupNode(binding.node3, 0.5f, 0.9f, "4th Floor Hallway");

        // Blue room nodes
        setupNode(binding.node5, 0.35f, 0.3f, "Room 406");
        setupNode(binding.node6, 0.35f, 0.55f, "Room 405");
        setupNode(binding.node7, 0.35f, 0.80f, "Room 404");
        setupNode(binding.node9, 0.65f, 0.105f, "Room 401");
        setupNode(binding.node10, 0.65f, 0.38f, "Room 402");
        setupNode(binding.node11, 0.65f, 0.67f, "Room 403");
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
            // Check if the location is a room or a hallway
            if (locationName.contains("Hallway") || locationName.equals("Lobby") || locationName.equals("Canteen") || locationName.equals("Court")) {
                Toast.makeText(getContext(), "Clicked on: " + locationName, Toast.LENGTH_SHORT).show();
            } else {
                // It's a room, so show the schedule dialog
                showScheduleDialog(locationName);
            }
        });

        node.bringToFront();
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
        // This is placeholder data. You can replace this with your actual schedule data source.
        switch (roomName) {
            case "Admission":
                return "Monday:\t\t9:00 AM - 11:00 AM\n" +
                        "Wednesday:\t\t1:00 PM - 3:00 PM\n" +
                        "Friday:\t\t10:00 AM - 12:00 PM";
            case "Clinic":
                return "Monday-Friday:\t\t8:00 AM - 5:00 PM";
            case "Registrar":
                return "Monday-Friday:\t\t8:30 AM - 4:30 PM";
            case "COMLAB1":
            case "COMLAB2":
            case "COMLAB3":
            case "COMLAB4":
                return "Tuesday:\t\t8:00 AM - 10:00 AM\n" +
                        "Thursday:\t\t2:00 PM - 4:00 PM";
            case "Teacher's Room":
                return "Monday-Friday:\t\t7:00 AM - 6:00 PM";
            case "IT Department":
            case "IT Office":
                return "Monday-Friday:\t\t8:00 AM - 5:00 PM (By Appointment)";
            case "BMMA LAB":
            case "Pastry Laboratory":
            case "Kitchen Laboratory":
                return "See department head for schedule.";
            case "Library (Books)":
            case "Library (Collection)":
                return "Monday-Saturday:\t\t7:30 AM - 6:00 PM";
            case "Room 201":
            case "Room 202":
            case "Room 203":
            case "Room 204":
            case "Room 205":
            case "Room 206":
            case "Room 301":
            case "Room 302":
            case "Room 303":
            case "Room 401":
            case "Room 402":
            case "Room 403":
            case "Room 404":
            case "Room 405":
            case "Room 406":
                return "Check university portal for class schedule.";
            default:
                return "No schedule available for this room.";
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
