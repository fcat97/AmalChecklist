package media.uqab.amalchecklist;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Amal.class},
        version = 1)
@TypeConverters({DataConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabaseInstance;

    public abstract AmalDao amalDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (appDatabaseInstance == null) {
            appDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "appDB")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallbacks)
                    .build();
        }

        return appDatabaseInstance;
    }

    private static RoomDatabase.Callback roomCallbacks = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Thread thread = new Thread(AppDatabase::populateDatabase);
            thread.start();
        }
    };

    private static void populateDatabase() {
        AmalDao dao = appDatabaseInstance.amalDao();

        dao.insert(new Amal("ফজরের সালাত পড়েছি", AmalType.DAILY));
        dao.insert(new Amal("সকালের মাসনুন যিকির করেছি", AmalType.DAILY));
        dao.insert(new Amal("যথাসময়ে সকল সালাত আদায় করেছি", AmalType.DAILY));
        dao.insert(new Amal("পাঁচ ওয়াক্তের সালাত মসজিদে আদায় করেছি", AmalType.DAILY));
        dao.insert(new Amal("প্রতি সালাত শেষে মাসনুন যিকির করেছি", AmalType.DAILY));
        dao.insert(new Amal("কোরআন তিলাওয়াত করেছি", AmalType.DAILY));
        dao.insert(new Amal("দ্বীনি জ্ঞানার্জনে নূন্যতম আধা ঘণ্টা সময় দিয়েছি", AmalType.DAILY));
        dao.insert(new Amal("মা-বাবা ও পরিবারের জন্য দোয়া করেছি", AmalType.DAILY));
        dao.insert(new Amal("পরিবারকে নিয়ে দ্বীনি তালিম করেছি", AmalType.DAILY));
        dao.insert(new Amal("মুসলিম উম্মাহর জন্য দোয়া করেছি", AmalType.DAILY));
        dao.insert(new Amal("সাদকাহ করেছি", AmalType.DAILY));
        dao.insert(new Amal("গিবত ও মিথ্যাচার থেকে বিরত থেকেছি", AmalType.DAILY));
        dao.insert(new Amal("রসূলুল্লাহ (সাঃ) এর ওপর দুরুদ পাঠ করেছি", AmalType.DAILY));
        dao.insert(new Amal("মানুষকে দাওয়াহ/নাসিহাহ করেছি", AmalType.DAILY));
        dao.insert(new Amal("দোয়া কবুলের সময় দোয়া করেছি", AmalType.DAILY));
        dao.insert(new Amal("কমপক্ষে ১০০ বার ইস্তিগফার পাঠ করেছি", AmalType.DAILY));
        dao.insert(new Amal("সন্ধার মাসনুন দোয়া আদায় করেছি", AmalType.DAILY));
        dao.insert(new Amal("তাহাজ্জুদের সালাত আদায় করেছি", AmalType.DAILY));
        dao.insert(new Amal("ঘুমানোর আগে সূরা মুলক তিলাওয়াত করেছি", AmalType.DAILY));
        dao.insert(new Amal("মৃত্যু পরবর্তী জীবন নিয়ে চিন্তা করেছি", AmalType.DAILY));
    }
}
