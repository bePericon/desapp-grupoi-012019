package app.service.account;

import app.model.account.Usuario;

public interface IUsuarioService {
    void create(Usuario usuarioPrueba);
    Usuario getById(Long id);
}
