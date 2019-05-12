package app.service.account;

import app.model.account.Usuario;
import app.persistence.account.UsuarioDao;
import app.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.Hashtable;
import java.util.List;

@Service
@Transactional
public class UsuarioService extends GenericService {

    @Autowired
    public UsuarioService(EntityManagerFactory factory, UsuarioDao usuarioDao) {
        super(factory, usuarioDao);
    }

    public boolean yaExiste(Usuario nuevoUsuario) {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("email", nuevoUsuario.getEmail());
        String s = "from Usuario where email = :email ";
        List<Usuario> list = (List<Usuario>) this.executeQueryList(s, hashtable);
        return list.size() > 0;
    }

    public boolean esValido(Usuario usuario){
        return true;
    }

    public Usuario getByEmailAndContrasenia(String email, String contrasenia) {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("email", email);
        hashtable.put("contrasenia", contrasenia);
        String s = "from Usuario where email = :email and contrasenia = :contrasenia";
        List<Usuario> list = (List<Usuario>) this.executeQueryList(s, hashtable);

        if(list.size() == 0){
//        return throw ExceptionUsuarioNoEncontrado();
        }
        return list.get(0);
    }
}