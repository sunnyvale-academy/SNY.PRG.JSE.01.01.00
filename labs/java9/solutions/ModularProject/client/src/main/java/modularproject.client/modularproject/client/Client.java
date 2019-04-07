package modularproject.client;

import java.util.ServiceLoader;
import modularproject.api.Service;

/**
 * Client
 */
public class Client {

    public String run(String providerType) {
        ServiceLoader<Service> srvLdr = ServiceLoader.load(Service.class);
        for (Service srv : srvLdr) {
            if (srv.getType().equals(providerType))
                return srv.execute().getMessage();
        }
        throw new RuntimeException("No suitable service provider found.");
    }

    public static void main(String[] args) {
        Client client = new Client();
        System.out.println(client.run("ProviderA"));
        System.out.println(client.run("ProviderB"));
    }
}