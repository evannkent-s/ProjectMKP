package id.ac.binus.project.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample data
        List<TransactionModel> transactions = new ArrayList<>();
        transactions.add(new TransactionModel("2024-12-01", "Success", 50000));
        transactions.add(new TransactionModel("2024-12-02", "Pending", 75000));
        transactions.add(new TransactionModel("2024-12-03", "Failed", 20000));

        TransactionAdapter adapter = new TransactionAdapter(transactions);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
