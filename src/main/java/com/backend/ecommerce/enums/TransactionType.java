package com.backend.ecommerce.enums;

public enum TransactionType {

    IMPS,
    UPI,
    COD;


    @Override
    public String toString() {
        return switch (this) {
            case COD -> "COD";
            case IMPS -> "IMPS";
            case UPI -> "UPI";
            default -> throw new IllegalArgumentException();
        };
    }
}
