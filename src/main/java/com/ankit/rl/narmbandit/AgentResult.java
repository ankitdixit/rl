package com.ankit.rl.narmbandit;

import java.util.ArrayList;

public class AgentResult
{
    int index = -1;
    ArrayList<Double> rewards;
    ArrayList<Boolean> isOptimal;
    int optimalMachineIndex;

    public AgentResult(int optimalMachineIndex)
    {
        this.optimalMachineIndex = optimalMachineIndex;
        this.rewards = new ArrayList();
        this.isOptimal = new ArrayList();
    }

    public void addStepResults(StepResults stepResults)
    {
        rewards.add(stepResults.getReward());
        isOptimal.add(optimalMachineIndex == stepResults.getChosenMachine()?true:false);
    }

    public int getSteps() {
        return rewards.size();
    }

    public ArrayList<Double> getRewards() {
        return rewards;
    }

    public ArrayList<Boolean> getIsOptimal() {
        return isOptimal;
    }
}
