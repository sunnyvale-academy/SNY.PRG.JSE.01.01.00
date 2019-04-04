package modularproject.providers;

import modularproject.api.Service;;
  
/**
 * ProviderA
 */
public class ProviderA implements Service{

    public String execute(){
        return this.toString();
    }

    @Override 
    public String toString(){
        return "I'm provider A";
    }
}