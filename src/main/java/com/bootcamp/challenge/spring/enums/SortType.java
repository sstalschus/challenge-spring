package com.bootcamp.challenge.spring.enums;

public enum SortType {

    ALPHABETICAL_ASC(0),
    ALPHABETICAL_DESC(1),
    PRICE_DESC(2),
    PRICE_ASC(3);

    private int code;

    SortType (int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static SortType valueOf(int code) throws IllegalAccessException {
        for(SortType value : SortType.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalAccessException("Invalid SortType code");
    }
}
