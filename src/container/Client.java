package container;

import java.util.*;

public class Client extends Thread{
    String name;
    List<Seed> seeds;
    final List<Container> containers;
    public Client(List<Seed> seeds, List<Container> containers) {
        this.seeds = seeds;
        this.containers = containers;
    }
    public boolean checkList() {
        return seeds.stream().anyMatch(seed -> seed.amount > 0);
    }

    public void buy() throws InterruptedException {
        Thread.sleep(2000);
        for (Container container : containers) {
            synchronized (containers) {
                if(Container.busy)
                    containers.wait();
                Container.busy = true;
                for (Seed seed : seeds) {
                    if (seed.name.equals(container.seed)) {
                        if (!(seed.amount == 0)){
                            container.getQuantity(seed);
                            Thread.sleep(500);
                        }

                    }
                }
                Container.busy = false;
                containers.notify();
            }
        }
    }

    @Override
    public void run() {
        while (checkList()) {
            if(Container.open) {
                try {
                    buy();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + " Finished");
    }
}
