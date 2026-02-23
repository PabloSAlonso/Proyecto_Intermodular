package ejem1;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("rest")
public class Aplicacion extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(Usuario.class);
        classes.add(Publicacion.class);
        classes.add(GestorUsuarios.class);
        return classes;
    }
}
