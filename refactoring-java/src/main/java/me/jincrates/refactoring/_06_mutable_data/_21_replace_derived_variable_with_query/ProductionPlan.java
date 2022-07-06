package me.jincrates.refactoring._06_mutable_data._21_replace_derived_variable_with_query;

import java.util.ArrayList;
import java.util.List;

public class ProductionPlan {

    private double production;
    private List<Double> adjustments = new ArrayList<>();

    public void applyAdjustment(double adjustment) {
        this.adjustments.add(adjustment);
        //this.production += adjustment;
    }

    public double getProduction() {
        //return calculatedProduction();
        return this.adjustments.stream().mapToDouble(Double::valueOf).sum();
    }

    // getProduction() 리턴값에 따라 해당 함수를 제거할 수 있다.
    private double calculatedProduction() {
        //자바 스트림
        //return this.adjustments.stream().reduce(0.0, (a, b) -> a + b);
        //return this.adjustments.stream().reduce(0.0, Double::sum);
        return this.adjustments.stream().mapToDouble(Double::valueOf).sum();
    }
}
