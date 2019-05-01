package service.account;

import model.account.UsuarioPrueba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.account.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioPrueba> list() {
        return usuarioRepository.findAll();
    }
}