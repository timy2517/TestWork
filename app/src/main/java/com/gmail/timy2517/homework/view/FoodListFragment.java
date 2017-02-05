package com.gmail.timy2517.homework.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.timy2517.homework.R;
import com.gmail.timy2517.homework.model.Food;
import com.gmail.timy2517.homework.model.FoodBank;

import java.util.List;

/**
 * Created by Artem Novik on 02.11.2016.
 */
public class FoodListFragment extends Fragment {

    private static final String ARG_CATEGORY_ID = "category_id";

    private RecyclerView mFoodRecyclerView;
    private int categoryId;

    public static FoodListFragment newInstance(int categoryId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CATEGORY_ID, categoryId);

        FoodListFragment fragment = new FoodListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        categoryId = (int) getArguments().getSerializable(ARG_CATEGORY_ID);

        View view = inflater.inflate(R.layout.fragment_food_list, container, false);
        mFoodRecyclerView = (RecyclerView) view.findViewById(R.id.food_recycler_view);
        mFoodRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        FoodBank mFoodBank = FoodBank.getInstance();

        List mFoodList = mFoodBank.getFoodListByCategoryId(categoryId);

        FoodAdapter adapter = new FoodAdapter(mFoodList);
        mFoodRecyclerView.setAdapter(adapter);
    }


    private class FoodHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mNameTextView;
        private TextView mWeightTextView;
        private TextView mPriceTextView;
        private ImageView mIconImageView;

        private Food mFood;

        public FoodHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_food_name);
            mWeightTextView = (TextView) itemView.findViewById(R.id.list_item_food_weight);
            mPriceTextView = (TextView) itemView.findViewById(R.id.list_item_food_price);
            mIconImageView = (ImageView) itemView.findViewById(R.id.list_item_food_image);
        }

        public void bindFood(Food food) {
            mFood = food;
            mNameTextView.setText(mFood.getName());
            mWeightTextView.setText(mFood.getWeight());
            mPriceTextView.setText(mFood.getPrice());
            mIconImageView.setImageResource(R.drawable.pizza);
        }

        @Override
        public void onClick(View v) {
            Intent intent = FoodPagerActivity.newIntent(getActivity(), mFood.getId());
            startActivity(intent);
        }
    }

    private class FoodAdapter extends RecyclerView.Adapter<FoodHolder> {

        private List<Food> mFoodList;

        public FoodAdapter(List<Food> foodList) {
            mFoodList = foodList;
        }


        @Override
        public FoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_food, parent, false);
            return new FoodHolder(view);
        }

        @Override
        public void onBindViewHolder(FoodHolder holder, int position) {
            Food food = mFoodList.get(position);
            holder.bindFood(food);
        }

        @Override
        public int getItemCount() {
            return mFoodList.size();
        }
    }
}
