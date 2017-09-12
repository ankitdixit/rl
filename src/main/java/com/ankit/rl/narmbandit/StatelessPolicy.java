package com.ankit.rl.narmbandit;

interface StatelessPolicy
{
    public int getMachineId(double[] meanEstimates);
    public String getName();
}