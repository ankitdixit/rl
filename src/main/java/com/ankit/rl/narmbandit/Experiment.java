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
        int optimalMachineIndex = environment.getOptimalMachineIndex();
        Map<Agent, AgentResult> agentResults = new HashMap<>(agents.size());
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

