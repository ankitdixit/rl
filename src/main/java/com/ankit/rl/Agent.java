package com.ankit.rl;

class Agent
{
    double[] meanEstimates;
    int[] countMachineChosen;
    StatelessPolicy statelessPolicy;
    Environment environment;
    public Agent(Environment environment, StatelessPolicy statelessPolicy, int noOfMachines)
    {
        this.statelessPolicy = statelessPolicy;
        this.environment = environment;
        this.meanEstimates = new double[noOfMachines];
        this.countMachineChosen = new int[noOfMachines];
    }
    public StepResults doAction()
    {
        int machineNumber = statelessPolicy.getMachineId(meanEstimates);
        double reward = environment.pullLever(machineNumber);
        meanEstimates[machineNumber] += ((reward - meanEstimates[machineNumber]) / (++countMachineChosen[machineNumber]));
        return new StepResults(machineNumber, reward);
    }

    public String getPolicyName()
    {
        return statelessPolicy.getName();
    }

}