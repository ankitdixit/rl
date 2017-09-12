package com.ankit.rl;

import static com.ankit.rl.Utils.getMaxValueIndex;

public class EpsillionGreedy implements StatelessPolicy
{
    final double epsilon;

    public EpsillionGreedy(double epsilon)
    {
        this.epsilon = epsilon;
    }

    @Override
    public int getMachineId(double[] meanEstimates)
    {
        int optimalMachineIndex = getMaxValueIndex(meanEstimates);
        if (Math.random() < epsilon)
        {
            int index = (int) Math.floor(Math.random() * (meanEstimates.length -1));
            return index < optimalMachineIndex ? index: index+1;
        }
        else {
            return optimalMachineIndex;
        }
    }

    @Override
    public String getName() {
        return "Epsilon_Greedy_"+epsilon;
    }

}
