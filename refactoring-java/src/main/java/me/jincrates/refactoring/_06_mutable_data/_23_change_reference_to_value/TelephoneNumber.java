package me.jincrates.refactoring._06_mutable_data._23_change_reference_to_value;

import java.util.Objects;

public class TelephoneNumber {

    private final String areaCode;

    private final String number;

    // 생성자 생성 후, setter 제거, equals() 구현, hashCode() 구현
    public TelephoneNumber(String areaCode, String number) {
        this.areaCode = areaCode;
        this.number = number;
    }

    public String areaCode() {
        return areaCode;
    }

    public String number() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TelephoneNumber)) return false;
        TelephoneNumber that = (TelephoneNumber) o;
        return Objects.equals(areaCode, that.areaCode) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaCode, number);
    }
}
