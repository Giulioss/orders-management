package it.ultraistinct.ordersmanagement.database.enums;

public enum OrderStatusEnum {

    OPEN("APERTA"),
    ON_GOING("IN CORSO"),
    CLOSED("CHIUSA");

    private final String value;

    OrderStatusEnum(String value) {
        this.value = value;
    }
}
