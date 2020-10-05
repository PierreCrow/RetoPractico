package com.appledroideirl.appuntomarcafreelancer.data.datasource.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.db.model.DbUsuario;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Converters;

@Database(entities = {
        DbUsuario.class,
},
        version = 1, exportSchema = false)
@TypeConverters({Converters.class})

public abstract class AppuntoDb extends RoomDatabase
{

    public static  int versionDb=1;


    private static AppuntoDb INSTANCE;
    public static final String DB_NAME = "appunto.db";
    private static Context mContext;


    public static AppuntoDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppuntoDb.class) {
                if (INSTANCE == null) {
                    mContext = context.getApplicationContext();
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppuntoDb.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    // Log.i(TAG, "populating with data...");
//                                    new PopulateDbAsync(INSTANCE).execute();
                                }
                            }).build();
                }
            }
        }
        return INSTANCE;
    }


 //   public abstract UserDao userDao();


}
