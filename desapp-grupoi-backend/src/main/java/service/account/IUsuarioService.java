package service.account;

import model.account.Usuario;

import java.util.List;

public interface IUsuarioService {

    List getAll();

    void create(Usuario usuario);
}
