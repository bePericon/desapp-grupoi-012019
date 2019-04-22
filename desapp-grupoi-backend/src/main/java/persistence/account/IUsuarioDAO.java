package persistence.account;

import model.account.Usuario;

import java.util.List;

public interface IUsuarioDAO {

    List<Usuario> getAll();
    void create(Usuario usario);
    void update(Usuario usario);
    Usuario getUsuarioById(long id);
    void delete(long id);
}
