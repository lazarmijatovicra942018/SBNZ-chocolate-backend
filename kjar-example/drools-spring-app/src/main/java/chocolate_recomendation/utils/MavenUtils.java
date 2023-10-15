package chocolate_recomendation.utils;

//import org.apache.maven.shared.invoker.*;

import org.apache.maven.shared.invoker.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
public class MavenUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MavenUtils.class);

    public static void mavenCleanAndInstallRules() throws MavenInvocationException {
        LOGGER.info("Running mvn clean install...");


        InvocationRequest request = new DefaultInvocationRequest();
     //   request.setPomFile( new File( "drools-spring-kjar\\pom.xml" ) );
        request.setPomFile( new File( "C:\\Users\\lazar\\OneDrive\\Desktop\\DIPLOMSKI\\SBNZ-chocolate-backend\\kjar-example\\drools-spring-kjar\\pom.xml" ) );

        request.setGoals( Arrays.asList( "clean", "install" ) );

        Invoker invoker = new DefaultInvoker();
        String home = System.getenv("MAVEN_HOME");
        invoker.setMavenHome(new File(home));
        InvocationResult result = invoker.execute( request );

        if (result.getExitCode() == 0) {
            LOGGER.info("mvn clean install successful");
        }
    }


}
