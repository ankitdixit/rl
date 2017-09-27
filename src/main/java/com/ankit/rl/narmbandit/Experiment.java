package com.ankit.rl.narmbandit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Experiment
{
    public final int NO_OF_STEPS;
    final Environment environment;
    final List<Agent> agents;

    public Experiment(Environment environment, List<Agent> agents, int NO_OF_STEPS)
    {
        this.environment = environment;
        this.agents = agents;
        this.NO_OF_STEPS = NO_OF_STEPS;
    }

    public Map<Agent, AgentResult> conductExperiment()
    {
        /**
         * Optimal machine index can be calculated before hand as we know the mean of the normal
         * distribution for each of the slot machine used.
         */
        int optimalMachineIndex = environment.getOptimalMachineIndex();
        /**
         * This will store the result for the current agent.
         * TODO
         * Question: Can we use more than one agent in one environment and if yes will there results be same or different?
         */
        Map<Agent, AgentResult> agentResults = new HashMap<>(agents.size());
        /**
         * TODO
         * Question: It looks like it is using multiple agent. What is the difference between them?
         * It looks like the policy used by these agents are different and essentially that is that
         * what we are measuring.
         *
         */
        for(Agent agent : agents) {
            AgentResult agentResult = new AgentResult(optimalMachineIndex);
            for (int step = 0; step < NO_OF_STEPS; step++) {
                agentResult.addStepResults(agent.doAction());
            }
            agentResults.put(agent, agentResult);
        }
        return agentResults;
    }

}

