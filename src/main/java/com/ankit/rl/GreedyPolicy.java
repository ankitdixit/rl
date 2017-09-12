package com.ankit.rl;

import static com.ankit.rl.Utils.getMaxValueIndex;

public class GreedyPolicy implements StatelessPolicy
{
    public int getMachineId(double[] meanEstimates)
    {
        return getMaxValueIndex(meanEstimates);
    }

    @Override
    public String getName() {
        return "Greedy Policy";
    }

}