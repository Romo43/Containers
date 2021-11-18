package container;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Container> containers = new ArrayList<>();

        containers.add(new Container("Chia", 15));
        containers.add(new Container("Rice", 10));
        containers.add(new Container("Oatmeal", 20));

        Farmer farmer1 = new Farmer("Chia", 3, "Rice", 2, containers);
        farmer1.setName("Farmer 1");
        Farmer farmer2 = new Farmer("Chia", 4, "Oatmeal", 2, containers);
        farmer2.setName("Farmer 2");
        Farmer farmer3 = new Farmer("Rice", 3, "Oatmeal", 3, containers);
        farmer3.setName("Farmer 3");
        Farmer farmer4 = new Farmer("Chia", 2, "Rice", 4, containers);
        farmer4.setName("Farmer 4");

        List<Seed> clientSeed1 = new ArrayList<>();
        List<Seed> clientSeed2 = new ArrayList<>();
        List<Seed> clientSeed3 = new ArrayList<>();
        List<Seed> clientSeed4 = new ArrayList<>();
        List<Seed> clientSeed5 = new ArrayList<>();

        Client client1 = new Client(clientSeed1, containers);
        client1.setName("Fernando");
        Client client2 = new Client(clientSeed2, containers);
        client2.setName("Pastor");
        Client client3 = new Client(clientSeed3, containers);
        client3.setName("Hiram");
        Client client4 = new Client(clientSeed4, containers);
        client4.setName("Juanpi");
        Client client5 = new Client(clientSeed5, containers);
        client5.setName("Carlos");

        clientSeed1.add(new Seed("Chia", 5));

        clientSeed2.add(new Seed("Chia", 5));
        clientSeed2.add(new Seed("Rice", 2));

        clientSeed3.add(new Seed("Oatmeal", 8));

        clientSeed4.add(new Seed("Chia", 1));
        clientSeed4.add(new Seed("Rice", 4));
        clientSeed4.add(new Seed("Oatmeal", 9));

        clientSeed5.add(new Seed("Chia", 6));
        clientSeed5.add(new Seed("Oatmeal", 7));

        farmer1.start();
        farmer2.start();
        farmer3.start();
        farmer4.start();

        client1.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();
    }
}
