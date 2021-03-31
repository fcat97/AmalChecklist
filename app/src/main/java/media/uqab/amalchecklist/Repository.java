package media.uqab.amalchecklist;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Repository {
    private AmalDao amalDao;

    public Repository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        this.amalDao = appDatabase.amalDao();
    }

    private Amal getAmal(String amalID) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<Amal> result = service.submit(() -> amalDao.getAmal(amalID));

        Amal amal = null;
        try {
            amal = result.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return amal;
    }

    public void update(Amal amal) {
        Amal a = getAmal(amal.amalID);
        Thread thread;
        if (a != null) {
            thread = new Thread(() -> {
                amalDao.update(amal);
            });
        } else {
            thread = new Thread(() -> {
                amalDao.insert(amal);
            });
        }
        thread.start();
    }

    public void delete(Amal amal) {
        Thread thread = new Thread(() -> {
            amalDao.delete(amal);
        });

        thread.start();
    }

    public LiveData<List<Amal>> getDailyAmal() {
        return amalDao.getDailyAmal();
    }

    public LiveData<List<Amal>> getWeeklyAmal() {
        return amalDao.getWeeklyAmal();
    }
}
