package com.ankit.rl.narmbandit;

public class SoftMaxPolicy
        implements StatelessPolicy
{
    /**
     * For high temperature, all the actions have nearly the same probability
     * For low temperature, the action with highest reward has probability 1.
     */
    private final double temperature;

    double[] machinePreviousEstimates = new double[10];
    double[] cachedNumerators = new double[10];

    public SoftMaxPolicy(double temperature)
    {
        this.temperature = temperature;
        for (int i=0; i < machinePreviousEstimates.length; i++)
        {
            machinePreviousEstimates[i] = -1;
        }
    }

    @Override
    public int getMachineId(double[] meanEstimates)
    {
        double machineProbs[] = new double[meanEstimates.length];
        double denominator = 0;
        for(int i =0; i < meanEstimates.length; i++)
        {
            /*if (machinePreviousEstimates[i] != meanEstimates[i]) {
                machinePreviousEstimates[i] = meanEstimates[i];
                cachedNumerators[i] = Math.exp(meanEstimates[i] / temperature);
            }
            machineProbs[i] = cachedNumerators[i];*/
            /**
             * If temperature value is very high then it will be 0 and the exp function will return 1
             * Hence the probability will be the same.
             * On the other hand if this is low then not 0, then estimates will act as a multiplicative factor and eventually
             * the action with the highest reward will have the highest probability.
             */
            machineProbs[i] = Math.exp(meanEstimates[i] / temperature);
            denominator += machineProbs[i];
        }

        /**
         * What is this?
         * Uniformly random value between 0 and 1. Then return that machine whose addition to th cummulative probability
         * makes it more than the random value ?
         * Not sure about this one:
         * In Rahul's code:
         *   return np.random.choice(list(range(len(values))), p=probabilities)
         *   He is returning a value based on the probabilities of the values.
         */
        double randomVal = Math.random();
        double cumulativeProb=0;
        for (int i=0; i<machineProbs.length; i++)
        {
            machineProbs[i] /= denominator;
            cumulativeProb += machineProbs[i];
            if (cumulativeProb >= randomVal) return i;
        }
        return machineProbs.length -1;
    }

    @Override
    public String getName()
        {
        return "softmax";
    }
}
