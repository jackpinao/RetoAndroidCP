package com.pinao.retoandroidcp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.pinao.retoandroidcp.R;
import com.pinao.retoandroidcp.databinding.FragmentHomeBinding;


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.onCreate();

        homeViewModel.getDescription().observe(getViewLifecycleOwner(), description -> {
            binding.textHome.setText(description);
        });
        homeViewModel.getImage().observe(getViewLifecycleOwner(), image -> {
            if (image != null && !image.isEmpty()) {
                Glide.with(this)
                        .load(image)
                        .into(binding.imageHome);
            }
        });

        // Carga la animación
        Animation slideAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_animation);
        binding.imageHome.startAnimation(slideAnimation);
        binding.textHome.startAnimation(slideAnimation);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}