package container;

import java.util.*;

public class Container {

    String seed;
    int amount, quantity;

    public static boolean full = false, busy = false, open = false;

    public Container(String seed, int amount) {
        this.seed = seed;
        this.amount = amount;
        this.quantity = 0;
    }

    public static boolean checkFilling(List<Container> containers) {
        full = true;
        for (Container container : containers) {
            if (!(container.amount == container.quantity)) {
                full = false;
                break;
            }
        }
        return full;
    }

    public static boolean verifyContainerEmpty(List<Container> containers) {
        return containers.stream().anyMatch(container -> container.quantity == 0);
    }

    public void setQuantity(int quantity) throws InterruptedException  {
        if (this.quantity + quantity >= amount) {
            System.out.println(Thread.currentThread().getName() + " filled the " + seed + " container");
            this.quantity = amount;

        } else {
            this.quantity += quantity;
            System.out.println(Thread.currentThread().getName() + " added " + quantity + " tons to the " + seed + " container");
        }
        Thread.sleep(500);
    }

    public synchronized void getQuantity(Seed seed) throws InterruptedException {
        System.out.println("Client " + Thread.currentThread().getName());
        if (this.quantity - seed.amount == 0) {
            System.out.println("Bought " + seed.amount + " tons of " + seed.name);
            System.out.println(seed.name + " container: " + this.quantity + " tons");System.out.println(this.quantity + "/" + amount);
            this.quantity = 0;
            seed.amount = 0;
        } else if (this.quantity - seed.amount < 0) {
            System.out.println("Bought " + (seed.amount + (this.quantity - seed.amount)) + " tons of " + seed.name);
            System.out.println(seed.name + " container: " + this.quantity + " tons");
            seed.amount -= this.quantity;
            this.quantity = 0;

        } else {
            this.quantity -= seed.amount;
            System.out.println("Bought " + seed.amount + " tons of " + seed.name);
            seed.amount = 0;
        }
        Thread.sleep(1000);
    }
}
