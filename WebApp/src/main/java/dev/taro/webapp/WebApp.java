package dev.taro.webapp;

import io.muzoo.ssc.assignment.tracker.SscAssignment;
import dev.taro.webapp.service.SecurityService;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
public class WebApp extends SscAssignment{
    public static void main(String[] args) {
        TomcatEnvironment.init();
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(TomcatEnvironment.getWorkDir().getAbsolutePath());
        tomcat.setPort(8082);
        tomcat.getConnector();

        SecurityService securityService = new SecurityService();
        ServletRouter servletRouter = new ServletRouter();
        servletRouter.setSecurityService(securityService);

        Context ctx = tomcat.addWebapp("", TomcatEnvironment.getDocBase().getAbsolutePath());

        servletRouter.init(ctx);

        try{
            tomcat.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        tomcat.getServer().await();
    }
}
