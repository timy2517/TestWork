package com.gmail.timy2517.homework.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.gmail.timy2517.homework.R;
import com.gmail.timy2517.homework.model.Food;
import com.gmail.timy2517.homework.model.FoodBank;

import java.util.List;

/**
 * Created by Artem Novik on 02.11.2016.
 */
public class FoodPagerActivity extends SingleFragmentActivity {

    private static final String EXTRA_FOOD_ID = "com.gmail.timy2517.homework.view.foodId";

    private List<Food> mFoodList;
    private FoodBank mFoodBank = FoodBank.getInstance();

    public static Intent newIntent(Context packageContext, int foodId) {
        Intent intent = new Intent(packageContext, FoodPagerActivity.class);
        intent.putExtra(EXTRA_FOOD_ID, foodId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_pager);

        final int foodId = (int) getIntent().getSerializableExtra(EXTRA_FOOD_ID);
        int categoryId = mFoodBank.getFood(foodId).getCategoryId();

        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_food_pager_view_pager);

        mFoodList = mFoodBank.getFoodListByCategoryId(categoryId);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Food food = mFoodList.get(position);
                return FoodFragment.newInstance(food.getId());
            }

            @Override
            public int getCount() {
                return mFoodList.size();
            }
        });

        for (int i = 0; i < mFoodList.size(); i++) {
            if (mFoodList.get(i).getId() == foodId) {
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
