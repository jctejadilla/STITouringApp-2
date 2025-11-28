package com.example.stitouringapp.ui.Tour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.example.stitouringapp.R;
import com.example.stitouringapp.databinding.FragmentTourBinding;

public class TourFragment extends Fragment {

    private FragmentTourBinding binding;

    // --- Building 1 Data ---
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

    // --- Annex Building Data ---
    private int currentAnnexIndex = 0;
    private final String[] annexBuildingNames = {
            "Annex - Ground Floor",
            "Annex - First Floor",
            "Annex - Second Floor",
            "Annex - Third Floor",
            "Annex - Auditorium"
            // Add more annex building names here
    };
    private final int[] annexBuildingImages = {
            R.drawable.annex_groundfloor,
            R.drawable.annex_firstfloor,
            R.drawable.annex_secondfloor,
            R.drawable.annex_thirdfloor,
            R.drawable.annex_auditorium,
            // Add more annex building images here
    };
    private final String[] annexMapIds = {
            "annex_ground_floor_map",
            "annex_first_floor_map",
            "annex_second_floor_map",
            "annex_third_floor_map",
            "annex_auditorium_map"
            // Add more annex map IDs here
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

        // --- Initial Setup ---
        updateBuildingInfo();
        updateAnnexBuildingInfo();

        // --- Building 1 Listeners ---
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

        // --- Annex Building Listeners ---
        binding.annexNextArrow.setOnClickListener(v -> {
            if (currentAnnexIndex < annexBuildingNames.length - 1) {
                currentAnnexIndex++;
                updateAnnexBuildingInfo();
            }
        });

        binding.annexPreviousArrow.setOnClickListener(v -> {
            if (currentAnnexIndex > 0) {
                currentAnnexIndex--;
                updateAnnexBuildingInfo();
            }
        });

        binding.annexImageCard.setOnClickListener(v -> {
            String selectedMapId = annexMapIds[currentAnnexIndex];
            NavDirections action = TourFragmentDirections.actionTourToInteractiveMap(selectedMapId);
            NavController navController = NavHostFragment.findNavController(TourFragment.this);
            navController.navigate(action);
        });
    }

    private void updateBuildingInfo() {
        if (binding != null) {
            binding.buildingText.setText(buildingNames[currentBuildingIndex]);
            binding.buildingImage.setImageResource(buildingImages[currentBuildingIndex]);
        }
    }

    private void updateAnnexBuildingInfo() {
        if (binding != null) {
            binding.annexBuildingText.setText(annexBuildingNames[currentAnnexIndex]);
            binding.annexBuildingImage.setImageResource(annexBuildingImages[currentAnnexIndex]);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
