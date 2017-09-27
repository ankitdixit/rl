package com.ankit.rl.narmbandit;

import static com.ankit.rl.narmbandit.Utils.getMaxValueIndex;

public class GreedyPolicy implements StatelessPolicy
{
    /**
     *
     * @param meanEstimates
     * @return The index of the machine whose mean estimate till now is higher. Given enough steps, this will be the slot machine
     * whose normal distribution has the highest mean.
     */
    public int getMachineId(double[] meanEstimates)
    {
        return getMaxValueIndex(meanEstimates);
    }

    @Override
    public String getName() {
        return "Greedy Policy";
    }

}