package dev.taro.webapp;
import dev.taro.webapp.service.SecurityService;
import dev.taro.webapp.servlet.*;
import jakarta.servlet.http.HttpServlet;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.util.ArrayList;
import java.util.List;
public class ServletRouter {

    private static final List<Class<? extends Routable>> routables = new ArrayList<>();

    static {
        routables.add(LoginServlet.class);
        routables.add(LogoutServlet.class);
        routables.add(ToDoListServlet.class);
        routables.add(DefaultServlet.class);
        routables.add(AddToDoServlet.class);
        routables.add(ToggleToDoServlet.class);
        routables.add(DeleteToDoServlet.class);
        routables.add(EditToDoServlet.class);
    }

    private SecurityService securityService;

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void init(Context ctx) {
        for (Class<? extends Routable> routableClass : routables) {
            try {
                Routable routable = routableClass.newInstance();
                routable.setSecurityService(securityService);
                String name = routable.getClass().getSimpleName();
                Tomcat.addServlet(ctx, name, (HttpServlet) routable);
                ctx.addServletMappingDecoded(routable.getMapping(), name);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
