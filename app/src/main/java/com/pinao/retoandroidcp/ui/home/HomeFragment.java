package com.pinao.retoandroidcp.ui.home;

import android.os.Bundle;
import android.os.Looper;
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
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private int currentIndex = 0;
    private final int imageChangeDelay = 3000;
    private List<String> imageUrls = new ArrayList<>();
    private List<String> description = new ArrayList<>();
    private Handler imageChangeHandler = new Handler(Looper.getMainLooper());
    private Runnable imageChangeRunnable = new Runnable() {
        @Override
        public void run() {
            if (!imageUrls.isEmpty() && !description.isEmpty()) {
                //Carga la imagen y el texto
                Glide.with(HomeFragment.this)
                        .load(imageUrls.get(currentIndex))
                        .into(binding.imageHome);
                binding.textHome.setText(description.get(currentIndex));
                //Carga la animación
                Animation slideAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_animation);
                binding.imageHome.startAnimation(slideAnimation);
                Animation slideAnimationText = AnimationUtils.loadAnimation(getContext(), R.anim.slide_animation);
                binding.textHome.startAnimation(slideAnimation);
                //Cambia el índice de la imagen y descripcion
                currentIndex = (currentIndex + 1) % imageUrls.size();
                //Reinicia el ciclo de cambio de imágenes
                imageChangeHandler.postDelayed(this, imageChangeDelay);
            }
        }
    };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);


        homeViewModel.onCreate();

//        homeViewModel.getDescription().observe(getViewLifecycleOwner(), description -> {
//            binding.textHome.setText(description);
//        });
//        homeViewModel.getImage().observe(getViewLifecycleOwner(), image -> {
//            if (image != null && !image.isEmpty()) {
//                Glide.with(this)
//                        .load(image)
//                        .into(binding.imageHome);
//                Animation slideAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_animation);
//                binding.imageHome.startAnimation(slideAnimation);
//            }
//        });
        homeViewModel.getImageUrls().observe(getViewLifecycleOwner(), urls -> {
            imageUrls = urls;
            // Inicia el ciclo de cambio de imágenes solo si aún no ha comenzado
            if (!imageChangeHandler.hasCallbacks(imageChangeRunnable)) {
                imageChangeHandler.postDelayed(imageChangeRunnable, imageChangeDelay);
            }
        });
        homeViewModel.getDescriptionUrls().observe(getViewLifecycleOwner(), newDescriptions -> {
            description = newDescriptions;
            // Reinicia el ciclo de cambio de imágenes y descripciones solo si aún no ha comenzado
            if (!imageChangeHandler.hasCallbacks(imageChangeRunnable)) {
                currentIndex = 0; // Reinicia el índice si es necesario
                imageChangeHandler.postDelayed(imageChangeRunnable, imageChangeDelay);
            }
        });

        // Carga la animación

//        binding.imageHome.startAnimation(slideAnimation);
//        binding.textHome.startAnimation(slideAnimation);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        imageChangeHandler.removeCallbacks(imageChangeRunnable);
        binding = null;
    }
}