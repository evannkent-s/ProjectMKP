package id.ac.binus.project.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import android.graphics.Color; // Tambahkan untuk mengakses warna

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ac.binus.project.R;
import id.ac.binus.project.adapter.TransactionAdapter;
import id.ac.binus.project.model.TransactionModel;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ImageButton filterButton;
    private Button buttonHistory, buttonSummary; // Tambahkan tombol
    private TransactionAdapter adapter;
    private List<TransactionModel> transactions, filteredList;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        filterButton = view.findViewById(R.id.filter_button);
        buttonHistory = view.findViewById(R.id.button_history); // Inisialisasi tombol
        buttonSummary = view.findViewById(R.id.button_summary);

        // Contoh data transaksi
        transactions = new ArrayList<>();
        transactions.add(new TransactionModel("RT123", "Rp 100.000,00", "Success", "PLN Prepaid", "2024-02-11"));
        transactions.add(new TransactionModel("RT124", "Rp 200.000,00", "Failed", "BCA", "2024-03-12"));

        adapter = new TransactionAdapter(transactions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);



        // Tombol History
        buttonHistory.setOnClickListener(v -> {
            buttonHistory.setSelected(true);
            buttonSummary.setSelected(false);

            buttonHistory.setTextColor(Color.WHITE);
            buttonSummary.setTextColor(Color.BLACK);
        });

        buttonSummary.setOnClickListener(v -> {
            buttonSummary.setSelected(true);
            buttonHistory.setSelected(false);

            buttonSummary.setTextColor(Color.WHITE);
            buttonHistory.setTextColor(Color.BLACK);
        });

        // Buka dialog filter
        filterButton.setOnClickListener(v -> {
            FilterDialogFragment dialog = new FilterDialogFragment();
            dialog.setFilterListener((startDate, endDate, status) -> {
                filteredList = new ArrayList<>();
                for (TransactionModel transaction : transactions) {
                    boolean dateMatch = (startDate.isEmpty() || transaction.getDate().compareTo(startDate) >= 0)
                            && (endDate.isEmpty() || transaction.getDate().compareTo(endDate) <= 0);
                    boolean statusMatch = status.isEmpty() || transaction.getStatus().equals(status);

                    if (dateMatch && statusMatch) {
                        filteredList.add(transaction);
                    }
                }

                // Update adapter dengan data yang sudah difilter
                adapter.updateList(filteredList);
            });
            dialog.show(getParentFragmentManager(), "filterDialog");
        });

        return view;
    }


}
