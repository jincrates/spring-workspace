package me.jincrates.refactoring._06_mutable_data._21_replace_derived_variable_with_query;

public class Discount {
    private double discount;

    private double baseTotal;

    public Discount(double baseTotal) {
        this.baseTotal = baseTotal;
    }

    public double getDiscountedTotal() {
        //실패시 이 위치에서 에러 발생
        //assert this.discountedTotal == this.baseTotal - this.discount;

        // 질의함수로 변경하여 파생변수 제거
        return this.baseTotal - this.discount;
    }

    public void setDiscount(double number) {
        this.discount = number;
    }
}
