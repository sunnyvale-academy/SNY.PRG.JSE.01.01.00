package modularproject.providers;


import modularproject.api.Service;
import modularproject.dto.Response;
import modularproject.dto.Payload;

/**
 * ProviderB
 */
public class ProviderB implements Service{

    @Override
    public Payload execute(){
        return new Response("Response from ProviderB");
    }

   
}