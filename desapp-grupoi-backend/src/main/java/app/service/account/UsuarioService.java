package app.service.account;

import app.error.exception.ExceptionBadRequest;
import app.error.exception.ExceptionConflict;
import app.error.exception.ExceptionNoContent;
import app.error.exception.ExceptionNotFound;
import app.model.account.Cuenta;
import app.model.account.Usuario;
import app.persistence.account.UsuarioDao;
import app.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class UsuarioService extends GenericService<Usuario> {

    @Autowired
    private UsuarioDao dao;

    @Autowired
    private CuentaService cuentaService;

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
            throw new ExceptionNotFound("El usuario no fue encontrado.");
        }
        if(usuario.getContrasenia() != contrasenia){
            throw new ExceptionConflict("La contraseña es incorrecta.");
        }
        return usuario;
    }

    public Usuario getByIdUsuario(long idUsuario) {
        Usuario usuario = this.getById(idUsuario);
        if(usuario == null)
            throw new ExceptionNotFound("El usuario no fue encontrado.");

        return usuario;
    }

    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = this.getAll();
        if (usuarios.isEmpty()) {
            throw new ExceptionNoContent("Lista de usuarios vacia.");
        }
        return usuarios;
    }

    public Usuario createNuevoUsuario(Usuario nuevoUsuario) {
        if(this.yaExiste(nuevoUsuario))
            throw new ExceptionConflict("Ya existe un usuario con email: " +nuevoUsuario.getEmail());

        if(!this.esValido(nuevoUsuario))
            throw new ExceptionBadRequest("Los datos del nuevo usuario no son validos.");

        this.save(Usuario.build(nuevoUsuario));
        Usuario usuario = this.getByEmailAndContrasenia(nuevoUsuario.getEmail(),nuevoUsuario.getContrasenia());
        Cuenta cuenta = new Cuenta(usuario);
        this.cuentaService.save(cuenta);
        return usuario;
    }

    public Usuario updateUsuario(long idUsuario, Usuario usuario) {
        Usuario usuarioActual = this.getByIdUsuario(idUsuario);
        usuarioActual.actualizar(usuario);
        this.save(usuarioActual);
        return usuarioActual;
    }

    public Usuario deleteByIdUsuario(long idUsuario) {
        Usuario usuario = this.getByIdUsuario(idUsuario);
        Cuenta cuenta = this.cuentaService.getDisponibleParaEliminar(usuario);
        this.cuentaService.deleteById(cuenta.getId());
        this.deleteById(idUsuario);
        return usuario;
    }
}