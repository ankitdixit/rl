package com.ankit.rl.narmbandit;

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
        /**
         * This is returning the id of the machine chosen for the next step.
         * THis is dependent on the policy and can be different for different policies.
         */
        int machineNumber = statelessPolicy.getMachineId(meanEstimates);
        /**
         * Get the reward for the machine selected.
         */
        double reward = environment.pullLever(machineNumber);

        /**
         * What is the estimate we have received from this machine till now.
         * The firs time it will be  = (reward-0)/1 ;
         */
        meanEstimates[machineNumber] += ((reward - meanEstimates[machineNumber]) / (++countMachineChosen[machineNumber]));
        return new StepResults(machineNumber, reward);
    }

    public String getPolicyName()
    {
        return statelessPolicy.getName();
    }

}