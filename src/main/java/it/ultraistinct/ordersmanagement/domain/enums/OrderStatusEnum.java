package it.ultraistinct.ordersmanagement.domain.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

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

    public static List<String> getAllValues() {
        return EnumSet.allOf(OrderStatusEnum.class).stream()
                .map(Enum::name)
                .toList();
    }
}
