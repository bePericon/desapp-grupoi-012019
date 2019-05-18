package app.service.account;

import app.error.exception.ExceptionUsuarioNoEncontrado;
import app.model.account.Usuario;
import app.persistence.account.UsuarioDao;
import app.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class UsuarioService extends GenericService<Usuario> {

    @Autowired
    private UsuarioDao dao;

    @Override
    protected UsuarioDao getDao() {
        return dao;
    }

    public UsuarioService() {
        super();
    }

    public boolean yaExiste(Usuario nuevoUsuario) {
        return this.getDao().getByEmail(nuevoUsuario.getEmail()) != null;
    }

    public boolean esValido(Usuario usuario){
        boolean nombreValido = !usuario.getNombre().isEmpty() && usuario.getNombre().length() <= 30;
        boolean apellidoValido = !usuario.getApellido().isEmpty() && usuario.getApellido().length() <= 30;

        Pattern p = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        Matcher m = p.matcher(usuario.getEmail());
        boolean emailValido = !usuario.getEmail().isEmpty() && m.find();
        boolean contraseniaValida = !usuario.getContrasenia().isEmpty() && usuario.getContrasenia().length() == 8;

        return nombreValido && apellidoValido && emailValido && contraseniaValida;
    }

    public Usuario getByEmailAndContrasenia(String email, String contrasenia) {
        Usuario usuario = this.getDao().getByEmail(email);
        if(usuario == null){
//        return throw ExceptionUsuarioNoEncontrado();
        }
        if(usuario.getContrasenia() != contrasenia){
//            return throw ExceptionUsuarioContraseniaIncorrecta();
        }
        return usuario;
    }

    public Usuario getByIdUsuario(long idUsuario) {
        Usuario usuario = (Usuario) this.getById(idUsuario);
        if(usuario == null)
            throw new ExceptionUsuarioNoEncontrado();

        return usuario;
    }
}