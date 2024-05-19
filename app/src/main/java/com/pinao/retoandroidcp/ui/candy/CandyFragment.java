package com.pinao.retoandroidcp.ui.candy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.pinao.retoandroidcp.databinding.FragmentCandyBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CandyFragment extends Fragment {

    private FragmentCandyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CandyViewModel dashboardViewModel =
                new ViewModelProvider(this).get(CandyViewModel.class);

        binding = FragmentCandyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}