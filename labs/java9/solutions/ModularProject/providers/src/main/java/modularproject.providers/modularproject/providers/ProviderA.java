package modularproject.providers;

import modularproject.api.Service;
import modularproject.dto.Response;
import modularproject.dto.Payload;
  
/**
 * ProviderA
 */
public class ProviderA implements Service{

    public Payload execute(){
        return new Response("Response from ProviderA");
    }

     
}