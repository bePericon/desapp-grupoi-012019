package app.persistence.account;

import app.model.account.UsuarioPrueba;

public interface IUsuarioDao {
    void create(UsuarioPrueba employee);

    void update(UsuarioPrueba employee);

    UsuarioPrueba getEmployeeById(long id);

    void delete(long id);
}
