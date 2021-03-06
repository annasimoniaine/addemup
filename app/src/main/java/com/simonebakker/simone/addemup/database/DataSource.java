package com.simonebakker.simone.addemup.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.simonebakker.simone.addemup.models.Game;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private final DBHelper dbHelper;

    public DataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    /**
     * Saves a game to the db, making it the current saved game
     * @param game: the game to save
     */
    public void saveGame(Game game) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GameContract.GameEntry.COLUMN_NAME_POINTS, game.getmPoints());
        values.put(GameContract.GameEntry.COLUMN_NAME_NAME, "");
        values.put(GameContract.GameEntry.COLUMN_NAME_PROGRESS, game.getmProgress());
        values.put(GameContract.GameEntry.COLUMN_NAME_DATE, game.getmDate());
        db.insert(GameContract.GameEntry.TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Gets the currently saved game
     */
    public Game getCurrentGame() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                GameContract.GameEntry.COLUMN_NAME_ID + ',' +
                GameContract.GameEntry.COLUMN_NAME_POINTS + ',' +
                GameContract.GameEntry.COLUMN_NAME_PROGRESS + ',' +
                GameContract.GameEntry.COLUMN_NAME_DATE  +
                " FROM " + GameContract.GameEntry.TABLE_NAME ;

        Game game = new Game(-1, 0, -1);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            game = new Game();
            game.setmID(cursor.getInt(cursor.getColumnIndex(GameContract.GameEntry.COLUMN_NAME_ID)));
            game.setmPoints(cursor.getInt(cursor.getColumnIndex(GameContract.GameEntry.COLUMN_NAME_POINTS)));
            game.setmProgress(cursor.getInt(cursor.getColumnIndex(GameContract.GameEntry.COLUMN_NAME_PROGRESS)));
            game.setmDate(cursor.getString(cursor.getColumnIndex(GameContract.GameEntry.COLUMN_NAME_DATE)));
        }
        cursor.close();
        db.close();

        return game;
    }

    /**
     * Removes all games from the db
     * Used before new currently saved game is added
     */
    public void removePrevious() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(GameContract.GameEntry.TABLE_NAME, null, null);
        db.close();
    }
}
