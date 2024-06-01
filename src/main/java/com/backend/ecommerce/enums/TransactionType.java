package com.backend.ecommerce.enums;

public enum TransactionType {

    DEPOSIT,
    WITHDRAWAL,
    TRANSFER,
    PAYMENT,
    REFUND;


    @Override
    public String toString() {
        return switch (this) {
            case DEPOSIT -> "Deposit";
            case WITHDRAWAL -> "Withdrawal";
            case TRANSFER -> "Transfer";
            case PAYMENT -> "Payment";
            case REFUND -> "Refund";
            default -> throw new IllegalArgumentException();
        };
    }
}
