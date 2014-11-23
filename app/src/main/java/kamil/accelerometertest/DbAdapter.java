package kamil.accelerometertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter extends SQLiteOpenHelper{

	public DbAdapter(Context context) {
		super(context, "database.db", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(
				"create table g_data(" +
				"id integer primary key autoincrement," +
				"date text," +
				"g integer);" +
				"");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void setGData(String date, int g){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues wartosci = new ContentValues();
		wartosci.put("date", date);
		wartosci.put("g", g);
		db.insertOrThrow("g_data", null, wartosci);
		
	}
	
	public Cursor getGData(){
		SQLiteDatabase db = getReadableDatabase();
		String[] kolumny = {"id", "date", "g"};
		Cursor kursor = db.query("g_data", kolumny, null, null, null, null, null);
		return kursor;
	}
	
	public void delGData(){
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("DELETE FROM g_data");
		db.execSQL("delete from sqlite_sequence where name='g_data'"); // rozpoczyna autoinkrementację od nowa!
		//resetGData(1);
	}

	

}
