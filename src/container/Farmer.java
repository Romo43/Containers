package container;

import java.util.*;

public class Farmer extends Thread {

    List<Seed> seeds = new ArrayList<>();
    final List<Container> containers;

    public Farmer(String seed1, int amount1, String seed2, int amount2, List<Container> containers) {
        seeds.add(new Seed(seed1, amount1));
        seeds.add(new Seed(seed2, amount2));
        this.containers = containers;
    }

    public void fillContainer() throws InterruptedException {
        Thread.sleep(500);
        for (Container container : containers)
            synchronized (containers) {
                if(Container.busy) {
                    containers.wait();
                }
                Container.busy = true;
                for (Seed seed : seeds) {
                    if (seed.name.equals(container.seed))
                        if (!(container.amount == container.quantity)) {
                            container.setQuantity(seed.amount);
                            Thread.sleep(1000);
                        }
                }
                Container.busy = false;
                containers.notify();
            }
    }
    @Override
    public void run() {
        while(true) {
            if(Container.verifyContainerEmpty(containers)) {
                while (!Container.checkFilling(containers)) {
                    Container.open = false;
                    try {
                        fillContainer();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Container.open = true;
            }
        }
    }
}
