//package com.ndtr.mylearningenglish.firebase;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.ndtr.mylearningenglish.models.User;
//
//import java.util.ArrayList;
//
//public class UserDatabaseHandler extends SQLiteOpenHelper {
//
//
//    public static final String TABLE_USER = "users";
//    public static final String COLUMN_ID = "id";
//    public static final String COLUMN_USERNAME = "username";
//    public static final String COLUMN_PASSWORD = "password";
//    public static final String COLUMN_FULLNAME = "fullName";
//    public static final String COLUMN_EMAIL = "email";
//
//    public static final String DATABASE_NAME = "userDB";
//    public static final int DATABASE_VERSION = 2;
//
//    private static final String DATABASE_CREATE = "create table "
//            + TABLE_USER + "(" + COLUMN_ID + " integer primary key autoincrement,"
//            + COLUMN_USERNAME + " text not null,"
//            + COLUMN_PASSWORD + " text not null,"
//            + COLUMN_FULLNAME + " text not null,"
//            + COLUMN_EMAIL + " text not null"
//            + ")";
//
//    public UserDatabaseHandler(Context context){
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(DATABASE_CREATE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
//        onCreate(db);
//    }
//
//    //Adding new user
//    public int addUser(User user){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USERNAME, user.getUserName());
//        values.put(COLUMN_PASSWORD, user.getPassword());
//        values.put(COLUMN_FULLNAME, user.getFullName());
//        values.put(COLUMN_EMAIL, user.getEmail());;
//        //Inserting Row
//        int id = (int) db.insert(TABLE_USER, null, values);
//        db.close();
//        return id;
//    }
//
//    public int editUser(User user, String position){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USERNAME, user.getUserName());
//        values.put(COLUMN_PASSWORD, user.getPassword());
//        values.put(COLUMN_FULLNAME, user.getFullName());
//        values.put(COLUMN_EMAIL, user.getEmail());;
//
//        return db.update(TABLE_USER, values, COLUMN_ID + " = ?",
//               new String[]{String.valueOf(position)} );
//    }
//    public User getUser(String username){
//        SQLiteDatabase db = this.getReadableDatabase();
//        User user = null;
////        Cursor cursor = db.query(TABLE_USER, new String[]{COLUMN_ID, COLUMN_USERNAME, COLUMN_PASSWORD}, COLUMN_USERNAME + "=?",
////                new String[] {String.valueOf(username)}, null, null, null, null);
////        if (cursor != null)
////            cursor.moveToFirst();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USERNAME + " = ?", new String[]{username});
//        if (cursor.getCount()>0) {
//            cursor.moveToFirst();
//            user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
//        }
//        db.close();
//        return user;
//
//
//    }
//
//    public  ArrayList<User> getAllUsers(){
//        ArrayList<User> userList = new ArrayList<User>();
//        String selectQuery = "SELECT * FROM " + TABLE_USER;
//        try{
//            SQLiteDatabase db = this.getReadableDatabase();
//            Cursor cursor = db.rawQuery(selectQuery, null);
//
//            if (cursor.moveToFirst()){
//                do{
//                    User user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
//                    userList.add(user);
//                }while (cursor.moveToNext());
//            }
//            db.close();
//            return userList;
//        } catch(Exception e){
//            return  null;
//        }
//
//    }
//}
