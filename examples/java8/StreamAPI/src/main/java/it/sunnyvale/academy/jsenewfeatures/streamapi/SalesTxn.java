package it.sunnyvale.academy.jsenewfeatures.streamapi;

import java.util.ArrayList;
import java.util.List;

/**
 * SalesTxn
 */
public class SalesTxn {

    public static class Builder {

        private String state;
        private String buyerName;
        private int txnId;
        private double transactionTotal;

        public SalesTxn.Builder txnId(int val) {
            this.txnId = val;
            return this;
        }

        public SalesTxn.Builder buyerName(String val) {
            this.buyerName = val;
            return this;
        }

        public SalesTxn.Builder state(String val) {
            this.state = val;
            return this;
        }

        public SalesTxn.Builder transactionTotal(double val) {
            this.transactionTotal = val;
            return this;
        }

        public SalesTxn build() {
            return new SalesTxn(this);
        }

    }

    private String state;
    private String buyerName;
    private int txnId;
    private double transactionTotal;

    public static List<SalesTxn> createTxnList() {
        SalesTxn txn1 = new SalesTxn();
        txn1.setState("IT");
        txn1.setBuyerName("Mario Rossi");
        txn1.setTxnId(1);
        txn1.setTransactionTotal(100.1);
        SalesTxn txn2 = new SalesTxn();
        txn2.setState("FR");
        txn2.setBuyerName("Mario Bianchi");
        txn2.setTxnId(2);
        txn2.setTransactionTotal(99.100);
        List<SalesTxn> list = new ArrayList<>();
        list.add(txn1);
        list.add(txn2);
        return list;
    }

    public SalesTxn(SalesTxn.Builder builder) {
        txnId = builder.txnId;
        buyerName = builder.buyerName;
        state = builder.state;
        transactionTotal = builder.transactionTotal;
    }

    public SalesTxn() {

    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    public void printSummary() {
        System.out.println(this);
    }

    /**
     * @return the buyerName
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * @param buyerName the buyerName to set
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * @return the txnId
     */
    public int getTxnId() {
        return txnId;
    }

    /**
     * @param txnId the txnId to set
     */
    public void setTxnId(int txnId) {
        this.txnId = txnId;
    }

    @Override
    public String toString() {
        return String.format("{txnId: %d, buyerName: %s, state: %s, transactionTotal: %f}", txnId, buyerName, state, transactionTotal);
    }

    /**
     * @return the transactionTotal
     */
    public double getTransactionTotal() {
        return transactionTotal;
    }

    /**
     * @param transactionTotal the transactionTotal to set
     */
    public void setTransactionTotal(double transactionTotal) {
        this.transactionTotal = transactionTotal;
    }

}