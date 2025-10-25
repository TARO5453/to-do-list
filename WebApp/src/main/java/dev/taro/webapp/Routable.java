package dev.taro.webapp;

import dev.taro.webapp.service.SecurityService;

public interface Routable {

    String getMapping();

    void setSecurityService(SecurityService securityService);
}
