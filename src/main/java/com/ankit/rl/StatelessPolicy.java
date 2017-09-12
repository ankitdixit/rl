package com.ankit.rl;

interface StatelessPolicy
{
    public int getMachineId(double[] meanEstimates);
    public String getName();
}