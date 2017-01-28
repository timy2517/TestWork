package com.gmail.timy2517.homework.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.timy2517.homework.R;
import com.gmail.timy2517.homework.model.Food;
import com.gmail.timy2517.homework.model.FoodBank;
import com.squareup.picasso.Picasso;


/**
 * Created by Artem Novik on 24.10.2016.
 */
public class FoodFragment extends Fragment {

    public static final String ARG_FOOD_ID = "food_id";
    private Food mFood;
    private TextView mName;
    private TextView mWeight;
    private TextView mPrice;
    private TextView mDescription;
    private ImageView mImage;
    FoodBank mFoodBank = FoodBank.getInstance();

    public static FoodFragment newInstance(int foodId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_FOOD_ID, foodId);

        FoodFragment fragment = new FoodFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int foodId = (int) getArguments().getSerializable(ARG_FOOD_ID);
        mFood = mFoodBank.getFood(foodId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_food, container, false);

        mName = (TextView) v.findViewById(R.id.name);
        mWeight = (TextView) v.findViewById(R.id.weight);
        mPrice = (TextView) v.findViewById(R.id.price);
        mDescription = (TextView) v.findViewById(R.id.description);
        mImage = (ImageView) v.findViewById(R.id.image);


        mName.setText(mFood.getName());
        mWeight.setText(mFood.getWeight());
        mPrice.setText(mFood.getPrice());
        mDescription.setText(mFood.getDescription());
        if (mFood.getImageUrl().trim().length() == 0) {
            Picasso.with(getContext())
                    .load(R.drawable.desert)
                    .into(mImage);
        } else {
            Picasso.with(getContext())
                    .load(mFood.getImageUrl())
                    .placeholder(R.drawable.desert)
                    .error(R.drawable.desert)
                    .into(mImage);
        }
        return v;
    }

}
