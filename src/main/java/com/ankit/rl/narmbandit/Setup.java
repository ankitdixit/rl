package com.ankit.rl.narmbandit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ankit.rl.narmbandit.LineChartPlotter.printChart;

public class Setup
{
    public static final int NO_OF_STEPS_PER_EXPERIMENT = 100;
    public static final int NO_OF_EXPERIMENTS = 100;
    public static final int NO_OF_MACHINES = 2;
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // Design can be improved, need not initialize
        Map<String, ArrayList<Double>> mapPolicyToTotalRewards = new HashMap<>();
        Map<String, ArrayList<Integer>> mapPolicyToOptimalCount = new HashMap<>();


        for(int experimentNumber=0; experimentNumber<NO_OF_EXPERIMENTS; experimentNumber++) {
            Environment environment = new Environment(NO_OF_MACHINES);
            List<Agent> agents = new ArrayList<>();

            /**
             * Adding different type of agents, with different policy for selecting different machines given a state.
             */
           // agents.add(new Agent(environment, new GreedyPolicy(), NO_OF_MACHINES));
            agents.add(new Agent(environment, new EpsillionGreedy(0.01), NO_OF_MACHINES));
//            agents.add(new Agent(environment, new EpsillionGreedy(0.05), NO_OF_MACHINES));
//            agents.add(new Agent(environment, new EpsillionGreedy(0.1), NO_OF_MACHINES));
//            agents.add(new Agent(environment, new SoftMaxPolicy(0.2), NO_OF_MACHINES));

            /**
             * Create an experiment with the Agents specified and the number of steps which they have to take.
             */
            Experiment experiment = new Experiment(environment, agents, NO_OF_STEPS_PER_EXPERIMENT);
            Map<Agent, AgentResult> experimentResult = experiment.conductExperiment();

            for (Agent agent : experimentResult.keySet()) {
                ArrayList<Double> rewardSums = mapPolicyToTotalRewards.get(agent.getPolicyName());
                ArrayList<Integer> optimalCountList = mapPolicyToOptimalCount.get(agent.getPolicyName());

                for (int step = 0; step < NO_OF_STEPS_PER_EXPERIMENT; step++) {
                    if (rewardSums == null) {
                        rewardSums = new ArrayList(Collections.nCopies(NO_OF_STEPS_PER_EXPERIMENT, 0.0));
                        mapPolicyToTotalRewards.put(agent.getPolicyName(), rewardSums);
                    }
                    if (optimalCountList == null) {
                        optimalCountList = new ArrayList(Collections.nCopies(NO_OF_STEPS_PER_EXPERIMENT, 0));
                        mapPolicyToOptimalCount.put(agent.getPolicyName(), optimalCountList);
                    }
                    double rewardSum = rewardSums.get(step);
                    rewardSums.set(step, rewardSum + experimentResult.get(agent).getRewards().get(step));
                    int totalCount = optimalCountList.get(step);
                    optimalCountList.set(step, totalCount + (experimentResult.get(agent).getIsOptimal().get(step) == true ? 1 : 0));
                }
            }
        }

        Map<String, ArrayList<Double>> mapAgentToAverageRewards = new HashMap<>();
        Map<String, ArrayList<Double>> mapOptimalChoicePercent = new HashMap<>();
        //normalise rewards and optimal % to plot

        for(int stepNumber = 0; stepNumber< NO_OF_STEPS_PER_EXPERIMENT; stepNumber++) {
            for(String policyName : mapPolicyToTotalRewards.keySet())
            {
                //calculate average reward
                double totalReward = mapPolicyToTotalRewards.get(policyName).get(stepNumber);
                if(mapAgentToAverageRewards.get(policyName) == null) {
                    mapAgentToAverageRewards.put(policyName, new ArrayList(Collections.nCopies(NO_OF_STEPS_PER_EXPERIMENT, 0.0)));
                    mapOptimalChoicePercent.put(policyName, new ArrayList(Collections.nCopies(NO_OF_STEPS_PER_EXPERIMENT, 0.0)));
                }
                mapAgentToAverageRewards.get(policyName).set(stepNumber, totalReward / NO_OF_EXPERIMENTS);

                //calculate optimal %
                int optimalCount = mapPolicyToOptimalCount.get(policyName).get(stepNumber);
                mapOptimalChoicePercent.get(policyName).set(stepNumber, optimalCount * 100.0 / NO_OF_EXPERIMENTS);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken "+(endTime - startTime) + " ns");

        List<PolicyPlotterInput> inputs = new ArrayList<>();
        for(String policyName : mapAgentToAverageRewards.keySet())
        {
            inputs.add(new PolicyPlotterInput(policyName, mapAgentToAverageRewards.get(policyName), mapOptimalChoicePercent.get(policyName)));
        }
        printChart(inputs);
    }
}
