package com.crudalchemy.codeclicker.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.crudalchemy.codeclicker.activity.Game;
import com.crudalchemy.codeclicker.models.Generator;
import com.crudalchemy.codeclicker.models.Upgrade;

@Database(entities = {Game.class, Generator.class, Upgrade.class}, version = 8)
public abstract class CodeClickerDatabase extends RoomDatabase
{
    public abstract CodeClickerDao dao();
}
