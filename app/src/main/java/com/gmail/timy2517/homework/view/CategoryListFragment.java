package com.gmail.timy2517.homework.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.timy2517.homework.R;
import com.gmail.timy2517.homework.downloadParse.FoodDownloader;
import com.gmail.timy2517.homework.model.Category;
import com.gmail.timy2517.homework.model.CategoryBank;
import com.gmail.timy2517.homework.model.FoodBank;

import java.util.List;

/**
 * Created by Artem Novik on 10.11.2016.
 */
public class CategoryListFragment extends Fragment {

    private RecyclerView mCategoryRecyclerView;
    private CategoryAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (!FoodBank.getInstance().isFoodBankReady()) {
            FoodDownloader mFoodDownloader = new FoodDownloader(getContext());
            mFoodDownloader.execute();
        }

        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        mCategoryRecyclerView = (RecyclerView) view.findViewById(R.id.category_recycler_view);
        mCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        CategoryBank mCategoryBank = CategoryBank.getInstance();
        List<Category> mCategories = mCategoryBank.getCategoryList();
        mAdapter = new CategoryAdapter(mCategories);
        mCategoryRecyclerView.setAdapter(mAdapter);
    }

    private class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitleTextView;
        public ImageView mIconImageView;

        Category mCategory;

        public CategoryHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_category_name);
            mIconImageView = (ImageView) itemView.findViewById(R.id.list_item_category_icon);
        }

        public void bindCategory(Category category) {
            mCategory = category;
            mTitleTextView.setText(mCategory.getName());
            mIconImageView.setImageResource(mCategory.getCategoryIconId());
        }

        @Override
        public void onClick(View v) {

            FragmentManager fm = getActivity().getSupportFragmentManager();

            while (true) {
                if (FoodBank.getInstance().isFoodBankReady()) {
                    fm.beginTransaction()
                            .replace(R.id.fragmentContainer, FoodListFragment.newInstance(mCategory.getCategoryId()))
                            .addToBackStack(null)
                            .commit();
                    break;
                }
            }
        }
    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

        private List<Category> mCategories;

        public CategoryAdapter(List<Category> categories) {
            mCategories = categories;
        }


        @Override
        public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_category, parent, false);
            return new CategoryHolder(view);
        }

        @Override
        public void onBindViewHolder(CategoryHolder holder, int position) {
            Category category = mCategories.get(position);
            holder.bindCategory(category);
        }

        @Override
        public int getItemCount() {
            return mCategories.size();
        }
    }
}
