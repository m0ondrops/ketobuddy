package com.example.ketobuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ketobuddy.R;
import com.example.ketobuddy.model.MealItem;

import java.util.List;

public class MealItemAdapter extends ArrayAdapter<MealItem> {

    public MealItemAdapter(@NonNull Context context, @NonNull List<MealItem> meals) {
        super(context, 0, meals);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MealItem meal = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_meal, parent, false);
        }

        TextView nameText = convertView.findViewById(R.id.foodName);
        TextView macrosText = convertView.findViewById(R.id.macros);

        // Show food name + quantity (e.g. "Avocado (150g)")
        String cleanName = meal.name.replaceAll("\\(\\s*\\d+(\\.\\d+)?g\\s*\\)", "").trim();
        nameText.setText(String.format("%s (%.0fg)", cleanName, meal.getQuantity()));

        // Show dynamic macros for current quantity
        macrosText.setText(String.format(
                "Kcal: %.0f | P: %.1fg | F: %.1fg | C: %.1fg",
                meal.getCalories(), meal.getProtein(), meal.getFat(), meal.getCarbs()
        ));

        return convertView;
    }
}

