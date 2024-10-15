package it.ultraistinct.ordersmanagement.domain.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    OPEN("APERTA"),
    ON_GOING("IN CORSO"),
    CLOSED("CHIUSA");

    private final String description;

    OrderStatusEnum(String description) {
        this.description = description;
    }

    public static OrderStatusEnum fromString(String value) {
        for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
            if (statusEnum.name().equalsIgnoreCase(value)) {
                return statusEnum;
            }
        }
        throw new IllegalArgumentException("Valore enum non valido: " + value);
    }
}
