package engine;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import compute.Compute;
import compute.Task;

public class ComputeEngine implements Compute {

    public ComputeEngine() {
    }

    @Override
    public <T> T executeTask(Task<T> task) throws RemoteException {
        return task.execute();
    }

    public static void main(String[] args) {

        Compute engine = new ComputeEngine();
        try {
            Compute stub = (Compute) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            String name = "Compute";
            registry.rebind(name, stub);
            System.out.println("ComputeEngine is ready to work");
        } catch (RemoteException e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
}