package com.pinao.retoandroidcp.domain.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pinao.retoandroidcp.R;
import com.pinao.retoandroidcp.domain.model.CandyItems;

import java.util.List;

public class CandyAdapter extends RecyclerView.Adapter<CandyAdapter.CandyViewHolder>{
    private final List<CandyItems> items;
    private final OnAddButtonClickListener listener;
    private final OnRemoveButtonClickListener listenerRemove;
    public CandyAdapter(List<CandyItems> items, OnAddButtonClickListener listener, OnRemoveButtonClickListener listenerRemove) {
        this.items = items;
        this.listener = listener;
        this.listenerRemove = listenerRemove;
    }
    @NonNull
    @Override
    public CandyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candy_item, parent, false);
        return new CandyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CandyViewHolder holder, int position) {
        CandyItems item = items.get(position);
        holder.bind(item, listener, listenerRemove);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    static class CandyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        TextView price;
        TextView quantity;
        Button addButton;
        Button removeButton;
        public CandyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantityTxt);
            addButton = itemView.findViewById(R.id.addButton);
            removeButton = itemView.findViewById(R.id.removeButton);
        }
        public void bind(CandyItems item, OnAddButtonClickListener listener, OnRemoveButtonClickListener listenerRemove) {
            name.setText(item.getName());
            description.setText(item.getDescription());
            price.setText(item.getPrice());

            quantity.setText("0");

            addButton.setOnClickListener(v -> {
                int currentQuantity = Integer.parseInt(quantity.getText().toString());
                currentQuantity++;
                //double finalPrice = Double.parseDouble(item.getPrice()) * currentQuantity;
                quantity.setText(String.valueOf(currentQuantity));
                listener.onAddButtonClick(item.getPrice());
                //listener.onAddButtonClick(String.valueOf(finalPrice));
            });
            removeButton.setOnClickListener(v -> {
                int currentQuantity = Integer.parseInt(quantity.getText().toString());
                if (currentQuantity > 0) {
                    currentQuantity--;
                    //double finalPrice = Double.parseDouble(item.getPrice()) * currentQuantity;
                    quantity.setText(String.valueOf(currentQuantity));
                    listenerRemove.onRemoveButtonClick(item.getPrice());
                    //listenerRemove.onRemoveButtonClick(String.valueOf(finalPrice));
                }
            });
        }
    }
    public void updateItems(List<CandyItems> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
    public interface OnAddButtonClickListener {
        void onAddButtonClick(String price);
    }
    public interface OnRemoveButtonClickListener {
        void onRemoveButtonClick(String price);
    }
}
