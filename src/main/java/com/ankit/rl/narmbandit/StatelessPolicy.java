package com.ankit.rl.narmbandit;

/**
 * Simple Interface for different policies. Like Greedy, Epsilon Greedy and Softmax
 */
interface StatelessPolicy
{
    public int getMachineId(double[] meanEstimates);
    public String getName();
}