package app.persistence.account;

import app.model.account.Usuario;

public interface IUsuarioDao {
//    void create(UsuarioPrueba employee);

//    void update(UsuarioPrueba employee);

    Usuario getById(long id);

//    void delete(long id);
}
