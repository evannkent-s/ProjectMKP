package id.ac.binus.project.model;

public class TransactionModel {
    private String referenceNo;
    private String amount;
    private String status;
    private String method;
    private String date;

    public TransactionModel(String referenceNo, String amount, String status, String method, String date) {
        this.referenceNo = referenceNo;
        this.amount = amount;
        this.status = status;
        this.method = method;
        this.date = date;
    }

    public String getReferenceNo() { return referenceNo; }
    public String getAmount() { return amount; }
    public String getStatus() { return status; }
    public String getMethod() { return method; }
    public String getDate() { return date; }
}
