package com.ankit.rl;

public class SoftMaxPolicy
        implements StatelessPolicy
{
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
            machineProbs[i] = Math.exp(meanEstimates[i] / temperature);
            denominator += machineProbs[i];
        }

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
