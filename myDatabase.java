package com.example.lloyd.healthnutritiontool;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Layout;
import android.view.View;
import android.widget.Toast;

import static com.example.lloyd.healthnutritiontool.R.layout.activity_profile;

/**
 * Created by Lloyd on 12/17/2017.
 */

public class myDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="Nutritional.sqlite";
    public static final String LOCATION = "/data/data/com.example.lloyd.healthnutritiontool/databases/";
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public myDatabase(Context context) {
        super(context,DATABASE_NAME,null,1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void OpenDatabase(){
        try {
            String dbPath = context.getDatabasePath(DATABASE_NAME).getPath();
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                return;
            }
            sqLiteDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
        }catch (Exception ee){
            System.out.print(ee.getMessage());
        }
    }
    public void closeDatabase(){
        if(sqLiteDatabase!=null){
            sqLiteDatabase.close();
        }
    }
}
