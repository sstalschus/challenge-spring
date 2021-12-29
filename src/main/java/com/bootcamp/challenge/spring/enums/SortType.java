package com.bootcamp.challenge.spring.enums;

public enum SortType {

    DESC(0),
    ASC(1);

    private int code;

    private SortType (int code){
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
        throw new IllegalAccessException("Invalid OrderStatus code");
    }
}
