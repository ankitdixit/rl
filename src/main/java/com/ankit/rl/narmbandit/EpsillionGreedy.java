package com.ankit.rl.narmbandit;

import static com.ankit.rl.narmbandit.Utils.getMaxValueIndex;

/**
 * This follows the greedy policy with a scope to explore from other machines with a probability of epsilon
 */
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
        /**
         * If random gives you a number less than epsilon the you explore.
         * Math.random gives a number between 0 and 1 with a uniform probability.
         */
        if (Math.random() < epsilon)
        {
            int index = (int) Math.floor(Math.random() * (meanEstimates.length -1));

            /**
             * TODO
             * What of Math.random() earlier returns 1, and then you add 1 to it in case that was the optimalMachineIndex?
             * Then this would give an index out of bound error.
             *              optimalMachineIndex = meanEstimates.length-1;
             *              int index = (int) Math.floor(1* (meanEstimates.length -1));
             *
             */
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
