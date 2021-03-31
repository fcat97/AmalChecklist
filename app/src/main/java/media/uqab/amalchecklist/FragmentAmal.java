package media.uqab.amalchecklist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class FragmentAmal extends Fragment {
    private RecyclerView recyclerView;
    private AmalAdapter adapter;
    private LiveData<List<Amal>> amalLiveData;
    private ItemClickListener itemClickListener;
    private ItemChangedListener itemChangedListener;

    private TextView day_1;
    private TextView day_2;
    private TextView day_3;
    private TextView day_4;
    private TextView day_5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_daily, null, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        day_1 = rootView.findViewById(R.id.day_1);
        day_2 = rootView.findViewById(R.id.day_2);
        day_3 = rootView.findViewById(R.id.day_3);
        day_4 = rootView.findViewById(R.id.day_4);
        day_5 = rootView.findViewById(R.id.day_5);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new AmalAdapter()
                .setListener(amal -> {
                    if (itemClickListener != null) itemClickListener.onClick(amal);
                })
                .setItemChangeListener(amal -> {
                    if (itemChangedListener != null) itemChangedListener.onChanged(amal);
                });
        recyclerView.setAdapter(adapter);
        if (amalLiveData != null) amalLiveData.observe(getViewLifecycleOwner(), adapter::submitList);

        // Set WeekDay names and Dates -------------------------------------------------------------
        Calendar calendar = Calendar.getInstance();
        String d = getWeekDayName(calendar.get(Calendar.DAY_OF_WEEK)) + "\n" + calendar.get(Calendar.DATE);
        day_1.setText(d);

        calendar.add(Calendar.DATE , -1);
        d = getWeekDayName(calendar.get(Calendar.DAY_OF_WEEK)) + "\n" + calendar.get(Calendar.DATE);
        day_2.setText(d);

        calendar.add(Calendar.DATE , -1);
        d = getWeekDayName(calendar.get(Calendar.DAY_OF_WEEK)) + "\n" + calendar.get(Calendar.DATE);
        day_3.setText(d);

        calendar.add(Calendar.DATE , -1);
        d = getWeekDayName(calendar.get(Calendar.DAY_OF_WEEK)) + "\n" + calendar.get(Calendar.DATE);
        day_4.setText(d);

        calendar.add(Calendar.DATE , -1);
        d = getWeekDayName(calendar.get(Calendar.DAY_OF_WEEK)) + "\n" + calendar.get(Calendar.DATE);
        day_5.setText(d);
    }

    private String getWeekDayName(int weeKDay) {
        if (weeKDay == 1) return "Sun";
        else if (weeKDay == 2) return "Mon";
        else if (weeKDay == 3) return "The";
        else if (weeKDay == 4) return "Wed";
        else if (weeKDay == 5) return "Thu";
        else if (weeKDay == 6) return "Fri";
        else return "Sat";
    }

    public FragmentAmal setAmalLiveData(LiveData<List<Amal>> amalLiveData) {
        this.amalLiveData = amalLiveData;
        return this;
    }

    public FragmentAmal setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        return this;
    }

    public FragmentAmal setItemChangedListener(ItemChangedListener itemChangedListener) {
        this.itemChangedListener = itemChangedListener;
        return this;
    }

    public interface ItemChangedListener {
        void onChanged(Amal amal);
    }
    public interface ItemClickListener {
        void onClick(Amal amal);
    }
}
