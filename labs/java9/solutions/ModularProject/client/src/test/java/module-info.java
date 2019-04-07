module modularproject.client{
    requires modularproject.api;
    uses  modularproject.api.Service;
    exports modularproject.client;

    requires junit;
} 