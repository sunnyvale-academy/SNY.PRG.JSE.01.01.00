package modularproject.providers;

import modularproject.api.Service;

/**
 * ProviderB
 */
public class ProviderB implements Service{

    public String execute(){
        return this.toString();
    }

    @Override
    public String toString(){
        return "I'm provider B";
    }
    
}