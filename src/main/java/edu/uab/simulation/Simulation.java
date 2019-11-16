package edu.uab.simulation;

import java.util.TimerTask;

public class Simulation extends TimerTask {

    public final World world;

    public Simulation(World world) {
        this.world = world;
    }

    @Override
    public void run() {
        world.nextTick();
        System.out.println(world);
    }
}
