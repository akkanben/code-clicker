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
import java.util.List;

public class UpgradeMenuRecyclerViewAdapter extends RecyclerView.Adapter<UpgradeMenuRecyclerViewAdapter.UpgradeListViewHolder>
{
    List<String> upgradeArrayList;
    Context callingActivity;

    public UpgradeMenuRecyclerViewAdapter(List<String> upgradeArrayList, Context callingActivity)
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
       TextView itemFragmentTextView = (TextView) holder.itemView.findViewById(R.id.fragment_upgrade_title_text_view);
       ImageView itemFragmentImageView = (ImageView) holder.itemView.findViewById(R.id.fragment_upgrade_image_view);
       String upgradeItemTitle = upgradeArrayList.get(position);

       itemFragmentTextView.setText(upgradeItemTitle);
       itemFragmentImageView.setBackgroundResource(R.drawable.computer);
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