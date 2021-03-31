package media.uqab.amalchecklist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FragmentAdd extends BottomSheetDialogFragment {
    private EditText label_et;
    private RadioButton daily_rb, weekly_rb;
    private Button confirmButton;
    private Amal amal;
    private ConfirmListener confirmListener;

    public FragmentAdd(Amal amal) {
        this.amal = amal;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add, null, false);

        label_et = view.findViewById(R.id.label_EditText);
        daily_rb = view.findViewById(R.id.rb_daily);
        weekly_rb = view.findViewById(R.id.rb_weekly);
        confirmButton = view.findViewById(R.id.confirm_button);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        label_et.setText(amal.label);
        daily_rb.setChecked(amal.type == AmalType.DAILY);
        weekly_rb.setChecked(amal.type == AmalType.WEEKLY);

        confirmButton.setOnClickListener(v -> {
            String label = label_et.getText().toString().trim();
            if (label.isEmpty()){
                Toast.makeText(getContext(), "Label is Empty", Toast.LENGTH_SHORT).show();
                return;
            }

            amal.label = label;
            if (daily_rb.isChecked()) amal.type = AmalType.DAILY;
            else if (weekly_rb.isChecked()) amal.type = AmalType.WEEKLY;

            if (confirmListener != null) {
                confirmListener.onConfirm(amal);
                dismiss();
            }
        });

    }

    public FragmentAdd setConfirmListener(ConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
        return this;
    }

    public interface ConfirmListener {
        void onConfirm(Amal amal);
    }
}
