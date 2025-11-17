package com.example.stitouringapp.ui.Tour;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.example.stitouringapp.MainActivity2;
import com.example.stitouringapp.R;
import com.example.stitouringapp.databinding.FragmentTourBinding;
import com.google.firebase.auth.FirebaseAuth;

public class TourFragment extends Fragment {

    FirebaseAuth mAuth;
    private FragmentTourBinding binding;
    private int currentBuildingIndex = 0;

    private final String[] buildingNames = {
            "Building 1 - Lobby",
            "Building 1 - Canteen & Court",
            "Building 1 - Mezzanine Floor",
            "Building 1 - Second Floor",
            "Building 1 - Third Floor",
            "Building 1 - Fourth Floor"
    };

    private final int[] buildingImages = {
            R.drawable.lobby,
            R.drawable.canteencourt,
            R.drawable.mezzanine,
            R.drawable.secondfloor,
            R.drawable.thirdfloor,
            R.drawable.fourthfloor
    };

    private final String[] mapIds = {
            "lobby_map",
            "canteen_map",
            "mezzanine_map",
            "second_floor_map",
            "third_floor_map",
            "fourth_floor_map"
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTourBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        updateBuildingInfo();

        binding.nextArrow.setOnClickListener(v -> {
            if (currentBuildingIndex < buildingNames.length - 1) {
                currentBuildingIndex++;
                updateBuildingInfo();
            }
        });

        binding.previousArrow.setOnClickListener(v -> {
            if (currentBuildingIndex > 0) {
                currentBuildingIndex--;
                updateBuildingInfo();
            }
        });

        binding.imageCard.setOnClickListener(v -> {
            String selectedMapId = mapIds[currentBuildingIndex];

            NavDirections action = TourFragmentDirections.actionTourToInteractiveMap(selectedMapId);

            NavController navController = NavHostFragment.findNavController(TourFragment.this);
            navController.navigate(action);
        });
    }

    private void updateBuildingInfo() {
        if(binding != null) {
            binding.buildingText.setText(buildingNames[currentBuildingIndex]);
            binding.buildingImage.setImageResource(buildingImages[currentBuildingIndex]);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}