package com.ankit.rl.narmbandit;

import java.util.ArrayList;

public class AgentResult
{
    int index = -1;
    ArrayList<Double> rewards;
    ArrayList<Boolean> isOptimal;
    int optimalMachineIndex;

    /**
     * This creates a Result class for the agent and is updated after each step.
     * @param optimalMachineIndex
     */
    public AgentResult(int optimalMachineIndex)
    {
        this.optimalMachineIndex = optimalMachineIndex;
        this.rewards = new ArrayList();
        this.isOptimal = new ArrayList();
    }

    public void addStepResults(StepResults stepResults)
    {
        // Save all the rewards
        rewards.add(stepResults.getReward());
        // If the reward is from the chose optical
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
