package example.khraisat.com.roomexample.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import example.khraisat.com.roomexample.model.ToDoModel;

/**
 * Created by Ayat khraisat  on 4/23/2018.
 * akhraisat@blessedtreeit.com
 * <p>
 * Project Name: National-Wallet
 * <p>
 * Blessed Tree IT
 */

/*Add all entities (tables) to the database (entities={x,y...etc})
  version is the Database version
  exportSchema is true by default but you can disable it
 for databases when you don't want to keep history of versions
 */
@Database(entities = {ToDoModel.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    //Add all created Dao here

    public abstract ToDoDao toDoDao();


}

