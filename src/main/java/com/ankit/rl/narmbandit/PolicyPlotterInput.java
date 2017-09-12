package com.ankit.rl.narmbandit;

import java.util.List;

public class PolicyPlotterInput
{
    private final String policyName;
    private final List<Double> averageRewardsPerStep;
    private final List<Double> optimalChoicePercentage;

    public PolicyPlotterInput(String policyName, List<Double> averageRewardsPerStep, List<Double> optimalChoicePercentage) {
        this.policyName = policyName;
        this.averageRewardsPerStep = averageRewardsPerStep;
        this.optimalChoicePercentage = optimalChoicePercentage;
    }

    public String getPolicyName() {
        return policyName;
    }

    public List<Double> getAverageRewardsPerStep() {
        return averageRewardsPerStep;
    }

    public List<Double> getOptimalChoicePercentage() {
        return optimalChoicePercentage;
    }
}
