package com.ankit.rl.narmbandit;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ankit.rl.narmbandit.Utils.getMaxValueIndex;

// Creating an environment
public class Environment {
    // Normal distribution for the Environment is Static
    static NormalDistribution normalDistribution = new NormalDistribution();
    private final int noOfMachines;
    // Machines to be used in this environment
    private ArrayList<SlotMachine> slotMachines;
    // Which is the most optimal machine amongst these machines. This will be learned later.
    // Ideally this should be the machine with the highest mean as eventually it will return the highest reward.
    private int optimalMachineIndex = -1;

    public Environment(int noOfMachines) {
        this.noOfMachines = noOfMachines;
        slotMachines = new ArrayList<>(noOfMachines);
        for (int i = 0; i < noOfMachines; i++) {
            slotMachines.add(createSlotMachine());
        }
    }

    public double pullLever(int machineId) {
        return slotMachines.get(machineId).pullLever();
    }

    private SlotMachine createSlotMachine() {
        // Create a new slot machine, whose mean is from a normal distribution and standard deviation is 1.
        return new SlotMachine(new NormalDistribution(normalDistribution.sample(), 1));
    }

    public int getOptimalMachineIndex() {
        if (optimalMachineIndex != -1) return optimalMachineIndex;
        List<Double> means = slotMachines.stream().mapToDouble(x -> x.normalDistribution.getMean()).boxed().collect(Collectors.toList());
        optimalMachineIndex = getMaxValueIndex(means);
        return optimalMachineIndex;
    }

    // Straight forward
    // Initialize a slot machine with a normal distribution and pulling the level will return a value from the normal distribution sample.
    private class SlotMachine {
        private final NormalDistribution normalDistribution;

        public SlotMachine(NormalDistribution normalDistribution) {
            this.normalDistribution = normalDistribution;
        }

        public double pullLever() {
            return normalDistribution.sample();
        }
    }
}