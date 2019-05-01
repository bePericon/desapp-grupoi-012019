package persistence.account;

import model.account.UsuarioPrueba;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioPrueba, Long> {

}