package com.crudalchemy.codeclicker.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.crudalchemy.codeclicker.R;
import com.crudalchemy.codeclicker.activity.Game;
import com.crudalchemy.codeclicker.models.Generator;

import java.util.List;

public class GeneratorMenuRecyclerViewAdapter extends RecyclerView.Adapter<GeneratorMenuRecyclerViewAdapter.UpgradeListViewHolder>
{
    Game game;
    Context callingActivity;

    public GeneratorMenuRecyclerViewAdapter(Game game, Context callingActivity)
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
       List<Generator> generatorList = game.getGeneratorList();
       Generator currentGenerator = generatorList.get(position);
       if (currentGenerator.isVisible()) {
           TextView itemFragmentTitleTextView = (TextView) holder.itemView.findViewById(R.id.fragment_upgrade_title_text_view);
           TextView itemFragmentDescriptionTextView = (TextView) holder.itemView.findViewById(R.id.fragment_upgrade_description_text_view);
           TextView itemFragmentCostTextView = (TextView) holder.itemView.findViewById(R.id.fragment_upgrade_cost_text_view);
           ImageView itemFragmentImageView = (ImageView) holder.itemView.findViewById(R.id.fragment_upgrade_image_view);

           String generatorItemTitle = currentGenerator.getName();
           String generatorItemDescription = currentGenerator.getDescription();
           String generatorItemCost = String.valueOf(currentGenerator.getNextPrice());
           int generatorItemImage = currentGenerator.getImage();

           itemFragmentTitleTextView.setText(generatorItemTitle);
           itemFragmentDescriptionTextView.setText(generatorItemDescription);
           itemFragmentCostTextView.setText((generatorItemCost));
           itemFragmentImageView.setBackgroundResource(generatorItemImage);

           Button purchaseButton = holder.itemView.findViewById(R.id.fragment_upgrade_purchase_button);
           if (currentGenerator.getNextPrice() > game.getCurrentLineCount()) {
               purchaseButton.setEnabled(false);
           } else {
               purchaseButton.setEnabled(true);
               purchaseButton.setOnClickListener(view -> {
                   game.buyGenerator(currentGenerator);
                   GeneratorMenuRecyclerViewAdapter.this.notifyItemChanged(position);
               });
           }
       }

   }

   @Override
   public int getItemCount()
   {
       int count = 0;
       for (Generator element : game.getGeneratorList()) {
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