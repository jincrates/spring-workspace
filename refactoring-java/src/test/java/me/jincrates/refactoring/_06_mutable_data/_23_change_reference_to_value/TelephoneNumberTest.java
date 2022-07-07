package me.jincrates.refactoring._06_mutable_data._23_change_reference_to_value;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TelephoneNumberTest {

    @Test
    void testEquals() {
        //TelephoneNumber equals(Object o)를 구현하지 않으면 해당 테스트는 false
        TelephoneNumber number1 = new TelephoneNumber("123", "1234");
        TelephoneNumber number2 = new TelephoneNumber("123", "1234");
        assertEquals(number1, number2);
    }

    @Test
    void testRecordEquals() {
        TelephoneNumberRecord number1 = new TelephoneNumberRecord("123", "1234");
        TelephoneNumberRecord number2 = new TelephoneNumberRecord("123", "1234");
        assertEquals(number1, number2);
    }
}