package id.ac.binus.project.model;

public class TransactionModel {
    private String date;
    private String status;
    private double amount;

    public TransactionModel(String date, String status, double amount) {
        this.date = date;
        this.status = status;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }
}
