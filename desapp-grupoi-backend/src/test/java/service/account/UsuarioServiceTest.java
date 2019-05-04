package service.account;

import app.App;
import app.service.account.UsuarioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = App.class)
public class UsuarioServiceTest {

    @Autowired
    UsuarioService usuarioService;

    @Test
    public void usuarioServiceAutowired(){
        assertNotNull(usuarioService);
    }

}
