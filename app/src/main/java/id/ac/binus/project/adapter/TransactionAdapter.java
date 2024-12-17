package id.ac.binus.project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import id.ac.binus.project.R;
import id.ac.binus.project.model.TransactionModel;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<TransactionModel> transactionList;

    public TransactionAdapter(List<TransactionModel> transactionList) {
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TransactionModel transaction = transactionList.get(position);
        holder.referenceNo.setText(transaction.getReferenceNo());
        holder.amount.setText(transaction.getAmount());
        holder.method.setText(transaction.getMethod());
        holder.date.setText(transaction.getDate());
        holder.status.setText(transaction.getStatus());

        switch (transaction.getStatus().toLowerCase()) {
            case "success":
                holder.status.setBackgroundResource(R.drawable.bg_status_success);
                break;
            case "failed":
                holder.status.setBackgroundResource(R.drawable.bg_status_failed);
                break;
            case "cancel":
                holder.status.setBackgroundResource(R.drawable.bg_status_cancel);
                break;
            default:
                holder.status.setBackgroundResource(0); // Default
                break;
        }
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView referenceNo, amount, status, method, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            referenceNo = itemView.findViewById(R.id.text_reference_no);
            amount = itemView.findViewById(R.id.text_amount);
            status = itemView.findViewById(R.id.text_status);
            method = itemView.findViewById(R.id.text_method);
            date = itemView.findViewById(R.id.text_date);
        }
    }

    public void updateList(List<TransactionModel> newList) {
        transactionList.clear();
        transactionList.addAll(newList);
        notifyDataSetChanged();
    }

}
