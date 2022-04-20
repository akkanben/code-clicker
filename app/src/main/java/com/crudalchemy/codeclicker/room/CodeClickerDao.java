package com.crudalchemy.codeclicker.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.crudalchemy.codeclicker.activity.Game;
import com.crudalchemy.codeclicker.models.Generator;
import com.crudalchemy.codeclicker.models.Upgrade;

import java.util.List;

@Dao
public interface CodeClickerDao
{
    @Insert
    public void insertGame(Game game);

    @Insert
    public void insertGenerator(Generator generator);

    @Insert
    public void insertUpgrade(Upgrade upgrade);

    @Query("SELECT * FROM Game")
    public Game find();

    @Query("DELETE FROM Game")
    public void clearGame();

    @Query("DELETE FROM Generator")
    public void clearGenerator();

    @Query("DELETE FROM Upgrade")
    public void clearUpgrade();

    @Query("SELECT * FROM Generator")
    public List<Generator> findAllGenerators();

    @Query("SELECT * FROM Upgrade")
    public List<Upgrade> findAllUpgrades();

}
