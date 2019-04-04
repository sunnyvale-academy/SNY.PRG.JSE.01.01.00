package modularproject.client;

import java.util.ServiceLoader;
import modularproject.api.Service;
import modularproject.dto.Payload;

/**
 * Client
 */
public class Client {

    public String run(Class<Service> providerType) {
        ServiceLoader<Service> srvLdr = ServiceLoader.load(providerType);
        for (Service srv : srvLdr) {
            if (srv.getClass().equals(providerType))
                return srv.execute().getMessage();
        }
        throw new RuntimeException("No suitable service provider found.");
    }
}