package app.persistence.account;

import app.model.account.UsuarioPrueba;
import org.hibernate.Session;

public interface IUsuarioDao {
//    void create(UsuarioPrueba employee);

//    void update(UsuarioPrueba employee);

    UsuarioPrueba getById(long id);

//    void delete(long id);
}
