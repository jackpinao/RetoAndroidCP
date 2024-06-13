package com.pinao.retoandroidcp.ui.candy;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pinao.retoandroidcp.R;
import com.pinao.retoandroidcp.databinding.FragmentCandyBinding;
import com.pinao.retoandroidcp.domain.adapter.CandyAdapter;
import com.pinao.retoandroidcp.domain.model.CandyItems;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CandyFragment extends Fragment implements CandyAdapter.OnAddButtonClickListener, CandyAdapter.OnRemoveButtonClickListener{

    private FragmentCandyBinding binding;
    private final List<CandyItems> candies = new ArrayList<>();
    private CandyAdapter adapter = new CandyAdapter(candies, this, this);
    private final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CandyViewModel dashboardViewModel =
                new ViewModelProvider(this).get(CandyViewModel.class);

        binding = FragmentCandyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerView;

        CandyViewModel candyViewModel = new ViewModelProvider(this).get(CandyViewModel.class);
        candyViewModel.onCreate();
        candyViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
            if (!items.isEmpty()) {
                candies.clear();
                candies.addAll(items);
                adapter = new CandyAdapter(candies, this, this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);

            }
        });
        binding.continueBtn.setOnClickListener(v -> {
            LayoutInflater inflater2 = requireActivity().getLayoutInflater();
            View dialogView = inflater2.inflate(R.layout.dialog_pay, null);
            String[] documents = new String[]{"DNI", "Carnet de extranjería", "Pasaporte"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, documents);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner spinner = dialogView.findViewById(R.id.tipeDocumentSpinner);
            spinner.setAdapter(adapter);

            new AlertDialog.Builder(requireActivity())
                    .setTitle("Pago")
                    .setView(dialogView)
                    .setPositiveButton("Pagar", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                    .show();
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onAddButtonClick(String price) {
        TextView total = binding.pricesTotalTXT;
        double totalValue = Double.parseDouble(total.getText().toString());
        total.setText(String.valueOf(totalValue + Double.parseDouble(price)));
    }

    @Override
    public void onRemoveButtonClick(String price) {
        TextView total = binding.pricesTotalTXT;
        double totalValue = Double.parseDouble(total.getText().toString());
        total.setText(String.valueOf(totalValue - Double.parseDouble(price)));
    }
}