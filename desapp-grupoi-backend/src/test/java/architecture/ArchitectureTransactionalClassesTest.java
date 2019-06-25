package architecture;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ArchitectureTransactionalClassesTest {
    
    @Test
    public void testArchitectureGenericService() {
        boolean result = true;

        try {
            Method[] methodsCuenta = Class.forName("service.account.CuentaService").getMethods();
            Method[] methodsUser = Class.forName("service.account.UsuarioService").getMethods();
            Method[] methodsGeneric = Class.forName("service.GenericService").getMethods();

            Method[] superMethodsEvento = Class.forName("service.event.EventoService").getSuperclass().getMethods();
            Method[] superMethodsInvitacion = Class.forName("service.event.InvitacionService").getSuperclass().getMethods();
            Method[] superMethodsItem = Class.forName("service.event.ItemService").getSuperclass().getMethods();
            Method[] superMethodsTemplate = Class.forName("service.event.TemplateService").getSuperclass().getMethods();

//            List<Method> allMethodsService = new ArrayList<>(Arrays.asList());

            List<Method> allMethodsService = new ArrayList<>(Arrays.asList(methodsCuenta));
            allMethodsService.addAll(new ArrayList<>(Arrays.asList(methodsUser)));
            allMethodsService.addAll(new ArrayList<>(Arrays.asList(methodsGeneric)));


            List<Method> superClassMethods = new ArrayList<>(Arrays.asList(superMethodsEvento));
            superClassMethods.addAll(new ArrayList<>(Arrays.asList(superMethodsInvitacion)));
            superClassMethods.addAll(new ArrayList<>(Arrays.asList(superMethodsItem)));
            superClassMethods.addAll(new ArrayList<>(Arrays.asList(superMethodsTemplate)));

            for (Method method : allMethodsService) {
                if ( !(method.getName().contains("Service") || superClassMethods.contains(method)) ) {
                    Annotation[] annotations = method.getAnnotations();
                    if (annotations.length == 0) {
                        result = false;
                    }
                    for (Annotation annotation : annotations) {
                        result &= annotation.toString().startsWith("@org.springframework.transaction.annotation.Transactional");
                    }
                }
            }

            Assert.assertTrue(result);

        } catch ( ClassNotFoundException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

}
