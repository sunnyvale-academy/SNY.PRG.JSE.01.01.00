package modularproject.providers;

import modularproject.api.Service;
import modularproject.dto.Response;
import modularproject.dto.Payload;
  
/**
 * ProviderA
 */
public class ProviderA implements Service{

    @Override
    public Payload execute(){
        return new Response("Response from ProviderA");
    }

    @Override
    public String getType() {
        return "ProviderA";
    }

     
}