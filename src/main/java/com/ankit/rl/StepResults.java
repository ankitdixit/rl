package com.ankit.rl;

public class StepResults
{
    final int machineChosen;
    final double reward;

    public StepResults(int machineChosen, double reward)
    {
        this.machineChosen = machineChosen;
        this.reward = reward;
    }

    public int getChosenMachine() {
        return machineChosen;
    }

    public double getReward() {
        return reward;
    }
}
