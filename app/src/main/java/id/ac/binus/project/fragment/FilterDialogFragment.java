package id.ac.binus.project.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import id.ac.binus.project.R;

public class FilterDialogFragment extends DialogFragment {

    private TextView fromDateText, toDateText;
    private Button applyButton, successButton, failedButton, allTransactionButton;
    private String fromDate = "", toDate = "", selectedStatus = "";

    public interface FilterListener {
        void onFilterApplied(String startDate, String endDate, String status);
    }

    private FilterListener listener;

    public void setFilterListener(FilterListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_filter, container, false);

        fromDateText = view.findViewById(R.id.from_date);
        toDateText = view.findViewById(R.id.to_date);
        applyButton = view.findViewById(R.id.apply_button);

        successButton = view.findViewById(R.id.status_success);
        failedButton = view.findViewById(R.id.status_failed);
        allTransactionButton = view.findViewById(R.id.status_all);

        // Atur pemilihan status
        successButton.setOnClickListener(v -> selectedStatus = "Success");
        failedButton.setOnClickListener(v -> selectedStatus = "Failed");
        allTransactionButton.setOnClickListener(v -> selectedStatus = "");

        // Atur pemilihan tanggal
        fromDateText.setOnClickListener(v -> showDatePickerDialog(true));
        toDateText.setOnClickListener(v -> showDatePickerDialog(false));

        // Terapkan filter
        applyButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onFilterApplied(fromDate, toDate, selectedStatus);
            }
            dismiss();
        });

        return view;
    }

    private void showDatePickerDialog(boolean isFromDate) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(getContext(),
                (view, year, month, dayOfMonth) -> {
                    String date = year + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", dayOfMonth);
                    if (isFromDate) {
                        fromDate = date;
                        fromDateText.setText(date);
                    } else {
                        toDate = date;
                        toDateText.setText(date);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}
