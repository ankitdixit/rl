package com.ankit.rl.narmbandit;

// This consists of result of a particular step. The machine chosen and the reward received by selecting it.
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
