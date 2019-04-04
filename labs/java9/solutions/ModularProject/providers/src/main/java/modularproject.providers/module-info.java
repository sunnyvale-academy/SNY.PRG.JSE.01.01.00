module modularproject.providers {
    requires modularproject.api;
    provides modularproject.api.Service with modularproject.providers.ProviderA, modularproject.providers.ProviderB;
}