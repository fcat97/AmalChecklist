package media.uqab.amalchecklist;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;


public class AmalAdapter extends ListAdapter<Amal, AmalAdapter.AmalHolder> {
    private ItemClickListener listener;
    private ItemChangeListener itemChangeListener;
    private Context context;

    protected AmalAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Amal> DIFF_CALLBACK = new DiffUtil.ItemCallback<Amal>() {
        @Override
        public boolean areItemsTheSame(@NonNull Amal oldItem, @NonNull Amal newItem) {
            return oldItem.amalID.equals(newItem.amalID);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Amal oldItem, @NonNull Amal newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public AmalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_amal, parent, false);

        context = parent.getContext();

        return new AmalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmalAdapter.AmalHolder holder, int position) {

        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
        @ColorInt int colorPrimary = typedValue.data;

        Amal amal = getItem(position);

        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) listener.onClick(amal);
            return true;
        });

        holder.itemView.setOnClickListener(v -> Toast.makeText(context, amal.label, Toast.LENGTH_SHORT).show());

        holder.label_tv.setText(amal.label);

        Calendar calendar = Calendar.getInstance();
        boolean day_1 = amal.history.getState(calendar);
        calendar.add(Calendar.DATE, -1);
        boolean day_2 = amal.history.getState(calendar);
        calendar.add(Calendar.DATE, -1);
        boolean day_3 = amal.history.getState(calendar);
        calendar.add(Calendar.DATE, -1);
        boolean day_4 = amal.history.getState(calendar);
        calendar.add(Calendar.DATE, -1);
        boolean day_5 = amal.history.getState(calendar);

        if (day_1) {
            holder.iv_day_1.setImageResource(R.drawable.ic_check);
            holder.iv_day_1.setColorFilter(colorPrimary);
        }
        else {
            holder.iv_day_1.setImageResource(R.drawable.ic_close);
            holder.iv_day_1.setColorFilter(Color.LTGRAY);
        }

        if (day_2) {
            holder.iv_day_2.setImageResource(R.drawable.ic_check);
            holder.iv_day_2.setColorFilter(colorPrimary);
        }
        else {
            holder.iv_day_2.setImageResource(R.drawable.ic_close);
            holder.iv_day_2.setColorFilter(Color.LTGRAY);
        }

        if (day_3) {
            holder.iv_day_3.setImageResource(R.drawable.ic_check);
            holder.iv_day_3.setColorFilter(colorPrimary);
        }
        else {
            holder.iv_day_3.setImageResource(R.drawable.ic_close);
            holder.iv_day_3.setColorFilter(Color.LTGRAY);
        }

        if (day_4) {
            holder.iv_day_4.setImageResource(R.drawable.ic_check);
            holder.iv_day_4.setColorFilter(colorPrimary);
        }
        else {
            holder.iv_day_4.setImageResource(R.drawable.ic_close);
            holder.iv_day_4.setColorFilter(Color.LTGRAY);
        }

        if (day_5) {
            holder.iv_day_5.setImageResource(R.drawable.ic_check);
            holder.iv_day_5.setColorFilter(colorPrimary);
        }
        else {
            holder.iv_day_5.setImageResource(R.drawable.ic_close);
            holder.iv_day_5.setColorFilter(Color.LTGRAY);
        }

        // Set onLongClickListener -----------------------------------------------------------------
        holder.iv_day_1.setOnLongClickListener(v -> {
            Calendar c = Calendar.getInstance();
            amal.history.commit(c, ! day_1);
            if (itemChangeListener != null) itemChangeListener.onChanged(amal);
            notifyItemChanged(position);
            return true;
        });

        holder.iv_day_2.setOnLongClickListener(v -> {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -1);
            amal.history.commit(c, ! day_2);
            if (itemChangeListener != null) itemChangeListener.onChanged(amal);
            notifyItemChanged(position);
            return true;
        });

        holder.iv_day_3.setOnLongClickListener(v -> {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -2);
            amal.history.commit(c, ! day_3);
            if (itemChangeListener != null) itemChangeListener.onChanged(amal);
            notifyItemChanged(position);
            return true;
        });

        holder.iv_day_4.setOnLongClickListener(v -> {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -3);
            amal.history.commit(c, ! day_4);
            if (itemChangeListener != null) itemChangeListener.onChanged(amal);
            notifyItemChanged(position);
            return true;
        });

        holder.iv_day_5.setOnLongClickListener(v -> {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -4);
            amal.history.commit(c, ! day_5);
            if (itemChangeListener != null) itemChangeListener.onChanged(amal);
            notifyItemChanged(position);
            return true;
        });

        // Set onClickListener ---------------------------------------------------------------------
        holder.iv_day_1.setOnClickListener(v -> Toast.makeText(context, "Hold to change", Toast.LENGTH_SHORT).show());
        holder.iv_day_2.setOnClickListener(v -> Toast.makeText(context, "Hold to change", Toast.LENGTH_SHORT).show());
        holder.iv_day_3.setOnClickListener(v -> Toast.makeText(context, "Hold to change", Toast.LENGTH_SHORT).show());
        holder.iv_day_4.setOnClickListener(v -> Toast.makeText(context, "Hold to change", Toast.LENGTH_SHORT).show());
        holder.iv_day_5.setOnClickListener(v -> Toast.makeText(context, "Hold to change", Toast.LENGTH_SHORT).show());


    }

    static class AmalHolder extends RecyclerView.ViewHolder {
        private TextView label_tv;
        private ImageView iv_day_1;
        private ImageView iv_day_2;
        private ImageView iv_day_3;
        private ImageView iv_day_4;
        private ImageView iv_day_5;

        public AmalHolder(@NonNull View itemView) {
            super(itemView);

            label_tv = itemView.findViewById(R.id.amal_label_tv);

            iv_day_1 = itemView.findViewById(R.id.day_1);
            iv_day_2 = itemView.findViewById(R.id.day_2);
            iv_day_3 = itemView.findViewById(R.id.day_3);
            iv_day_4 = itemView.findViewById(R.id.day_4);
            iv_day_5 = itemView.findViewById(R.id.day_5);
        }
    }


    public AmalAdapter setListener(ItemClickListener listener) {
        this.listener = listener;
        return this;
    }

    public AmalAdapter setItemChangeListener(ItemChangeListener itemChangeListener) {
        this.itemChangeListener = itemChangeListener;
        return this;
    }

    public interface ItemClickListener {
        void onClick(Amal amal);
    }

    public interface ItemChangeListener {
        void onChanged(Amal amal);
    }
}
