package com.ankit.rl;

import java.util.List;

public final class Utils
{
    public static int getMaxValueIndex(double[] meanEstimates)
    {
        int maxIndex = -1;
        double maxValue = Integer.MIN_VALUE;
        for(int i=0; i<meanEstimates.length; i++)
        {
            if(meanEstimates[i] > maxValue)
            {
                maxIndex = i;
                maxValue = meanEstimates[i];
            }
        }
        return maxIndex;
    }

    public static int getMaxValueIndex(List<Double> meanEstimates)
    {
        int maxIndex = -1;
        double maxValue = Integer.MIN_VALUE;
        for(int i=0; i<meanEstimates.size(); i++)
        {
            if(meanEstimates.get(i) > maxValue)
            {
                maxIndex = i;
                maxValue = meanEstimates.get(i);
            }
        }
        return maxIndex;
    }
}
