package app.service.account;

import app.model.account.UsuarioPrueba;

public interface IUsuarioService {
    void create(UsuarioPrueba usuarioPrueba);
    UsuarioPrueba getById(Long id);
}
