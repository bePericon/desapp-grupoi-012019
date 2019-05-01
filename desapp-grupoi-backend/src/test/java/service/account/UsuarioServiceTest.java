package service.account;

import model.account.UsuarioPrueba;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.account.UsuarioService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=hello.App.class)
public class UsuarioServiceTest {

    @Autowired(required=true)
    private UsuarioService usuarioService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<UsuarioPrueba> books = usuarioService.list();

        Assert.assertEquals(books.size(), 3);
    }
}