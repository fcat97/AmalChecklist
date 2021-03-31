package media.uqab.amalchecklist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private LiveData<List<Amal>> dailyAmal;
    private LiveData<List<Amal>> weeklyAmal;

    private FragmentAmal fragmentDaily;
    private FragmentAmal fragmentWeekly;
    private ImageButton addButton;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new Repository(getApplication());
        dailyAmal = repository.getDailyAmal();
        weeklyAmal = repository.getWeeklyAmal();

        fragmentDaily = new FragmentAmal()
                .setAmalLiveData(dailyAmal)
                .setItemClickListener(this::openFragmentAdd)
                .setItemChangedListener(this::updateAmal);
        fragmentWeekly = new FragmentAmal()
                .setAmalLiveData(weeklyAmal)
                .setItemClickListener(this::openFragmentAdd)
                .setItemChangedListener(this::updateAmal);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle()));
        tabLayout = findViewById(R.id.tab_layout);
        new TabPagerBinder(tabLayout, viewPager);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            openFragmentAdd(new Amal("", AmalType.DAILY));
        });
    }

    private void updateAmal(Amal amal) {
        repository.update(amal);
    }

    private void openFragmentAdd(Amal amal) {
        FragmentAdd fragmentAdd = new FragmentAdd(amal)
                .setConfirmListener(this::updateAmal);
        fragmentAdd.show(getSupportFragmentManager(), "Fragment Add");
    }

    private final class ViewPagerAdapter extends FragmentStateAdapter {
        public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return fragmentDaily;
                case 1:
                    return fragmentWeekly;
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}