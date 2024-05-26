package client;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import compute.Compute;

public class ComputeE {
    public static void main(String args[]) {
        try {
            Registry registry = LocateRegistry.getRegistry(args[0]);
            String name = "Compute";
            Compute comp = (Compute) registry.lookup(name);
            E task = new E(Integer.parseInt(args[1]));
            BigDecimal e = comp.executeTask(task);
            System.out.println(e);
        } catch (Exception e) {
            System.err.println("ComputeE exception:");
            e.printStackTrace();
        }
    }
}