package com.crudalchemy.codeclicker.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.crudalchemy.codeclicker.R;
import com.crudalchemy.codeclicker.activity.Game;
import com.crudalchemy.codeclicker.models.Generator;

import java.util.ArrayList;
import java.util.List;

public class GeneratorMenuRecyclerViewAdapter extends RecyclerView.Adapter<GeneratorMenuRecyclerViewAdapter.GeneratorListViewHolder>
{
    Game game;
    Context callingActivity;
    SoundPool soundPool;
    int buySound;

    public GeneratorMenuRecyclerViewAdapter(Game game, Context callingActivity)
    {
        this.game = game;
        this.callingActivity = callingActivity;

    }

    @NonNull
    @Override
    public GeneratorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View upgradeItemFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_upgrade_item, parent, false);
        AudioAttributes audioAttributes = new AudioAttributes
                .Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool
                .Builder()
                .setMaxStreams(1)
                .setAudioAttributes(audioAttributes)
                .build();
        buySound = soundPool.load(callingActivity, R.raw.clothbelt, 1);
        return new GeneratorListViewHolder(upgradeItemFragment);
    }

   @Override
   public void onBindViewHolder(@NonNull GeneratorListViewHolder holder, int position)
   {
       Generator currentGenerator = game.getActiveGeneratorList().get(position);
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
               soundPool.play(buySound,1,1,1,0,1);
               purchaseButton.setEnabled(false);
               GeneratorMenuRecyclerViewAdapter.this.notifyItemChanged(position);
           });
       }
   }

   @Override
   public int getItemCount()
   {
       return game.getActiveGeneratorList().size();
   }

    public static class GeneratorListViewHolder extends RecyclerView.ViewHolder
    {
        public GeneratorListViewHolder(View fragmentItemView)
        {
            super(fragmentItemView);
        }
    }
}