package com.crudalchemy.codeclicker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crudalchemy.codeclicker.R;
import com.crudalchemy.codeclicker.activity.Game;
import com.crudalchemy.codeclicker.models.Generator;
import com.crudalchemy.codeclicker.models.Upgrade;

import java.util.ArrayList;
import java.util.List;

public class UpgradeMenuRecyclerViewAdapter extends RecyclerView.Adapter<UpgradeMenuRecyclerViewAdapter.UpgradeListViewHolder>
{
    Game game;
    Context callingActivity;

    public UpgradeMenuRecyclerViewAdapter(Game game, Context callingActivity)
    {
        this.game = game;
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
       List<Upgrade> upgradeArrayList = new ArrayList<>();
       for (Upgrade upgrade : game.getUpgradeList()) {
           if (upgrade.isVisible())
               upgradeArrayList.add(upgrade);
       }
       Upgrade currentUpgrade = upgradeArrayList.get(position);
       if (currentUpgrade.isVisible()) {
           TextView itemFragmentTitleTextView = (TextView) holder.itemView.findViewById(R.id.fragment_upgrade_title_text_view);
           TextView itemFragmentDescriptionTextView = (TextView) holder.itemView.findViewById(R.id.fragment_upgrade_description_text_view);
           TextView itemFragmentCostTextView = (TextView) holder.itemView.findViewById(R.id.fragment_upgrade_cost_text_view);
           ImageView itemFragmentImageView = (ImageView) holder.itemView.findViewById(R.id.fragment_upgrade_image_view);

           String upgradeItemTitle = currentUpgrade.getName();
           String upgradeItemDescription = currentUpgrade.getDescription();
           String upgradeItemCost = String.valueOf(currentUpgrade.getCost());
           int upgradeItemImage = currentUpgrade.getImage();

           itemFragmentTitleTextView.setText(upgradeItemTitle);
           itemFragmentDescriptionTextView.setText(upgradeItemDescription);
           itemFragmentCostTextView.setText((upgradeItemCost));
           itemFragmentImageView.setBackgroundResource(upgradeItemImage);

           Button purchaseButton = holder.itemView.findViewById(R.id.fragment_upgrade_purchase_button);
           if (currentUpgrade.getCost() > game.getCurrentLineCount()) {
               purchaseButton.setEnabled(false);
           } else {
               purchaseButton.setEnabled(true);
               purchaseButton.setOnClickListener(view -> {
                   game.buyUpgrade(currentUpgrade);
                   UpgradeMenuRecyclerViewAdapter.this.notifyItemChanged(position);
               });
           }
       }
   }

   @Override
   public int getItemCount()
   {
       int count = 0;
       for (Upgrade element : game.getUpgradeList()) {
          if (element.isVisible())
              count++;
       }
       return count;
   }

    public static class UpgradeListViewHolder extends RecyclerView.ViewHolder
    {
        public UpgradeListViewHolder(View fragmentItemView)
        {
            super(fragmentItemView);
        }
    }
}