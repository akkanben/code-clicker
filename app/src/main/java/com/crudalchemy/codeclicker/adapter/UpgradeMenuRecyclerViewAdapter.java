package com.crudalchemy.codeclicker.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crudalchemy.codeclicker.R;
import com.crudalchemy.codeclicker.models.Generator;

import java.util.List;

public class UpgradeMenuRecyclerViewAdapter extends RecyclerView.Adapter<UpgradeMenuRecyclerViewAdapter.UpgradeListViewHolder>
{
    List<Generator> upgradeArrayList;
    Context callingActivity;

    public UpgradeMenuRecyclerViewAdapter(List<Generator> upgradeArrayList, Context callingActivity)
    {
        this.upgradeArrayList = upgradeArrayList;
        this.callingActivity = callingActivity;
    }

    @NonNull
    @Override
    public UpgradeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View upgradeItemFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_upgrade_item, parent, false);
        return new UpgradeListViewHolder(upgradeItemFragment);
    }

   @Override
   public void onBindViewHolder(@NonNull UpgradeListViewHolder holder, int position)
   {
       TextView itemFragmentTitleTextView = (TextView) holder.itemView.findViewById(R.id.fragment_upgrade_title_text_view);
       TextView itemFragmentDescriptionTextView = (TextView) holder.itemView.findViewById(R.id.fragment_upgrade_description_text_view);

       ImageView itemFragmentImageView = (ImageView) holder.itemView.findViewById(R.id.fragment_upgrade_image_view);
       String upgradeItemTitle = upgradeArrayList.get(position).getName();
       String upgradeItemDescription = upgradeArrayList.get(position).getDescription();
       int upgradeItemImage = upgradeArrayList.get(position).getImage();

       itemFragmentTitleTextView.setText(upgradeItemTitle);
       itemFragmentDescriptionTextView.setText(upgradeItemDescription);
       itemFragmentImageView.setBackgroundResource(upgradeItemImage);

   }

   @Override
   public int getItemCount()
   {
       return upgradeArrayList.size();
   }

    public static class UpgradeListViewHolder extends RecyclerView.ViewHolder
    {
        public UpgradeListViewHolder(View fragmentItemView)
        {
            super(fragmentItemView);
        }
    }
}