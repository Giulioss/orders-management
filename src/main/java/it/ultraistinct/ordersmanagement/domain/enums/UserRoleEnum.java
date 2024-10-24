package it.ultraistinct.ordersmanagement.domain.enums;

import lombok.Getter;

@Getter
public enum UserRoleEnum {

    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE");

    private final String description;

    UserRoleEnum(String description) {
        this.description = description;
    }

    public static UserRoleEnum fromString(String value) {
        for (UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
            if (userRoleEnum.name().equalsIgnoreCase(value)) {
                return userRoleEnum;
            }
        }
        throw new IllegalArgumentException("Valore enum non valido: " + value);
    }
}
