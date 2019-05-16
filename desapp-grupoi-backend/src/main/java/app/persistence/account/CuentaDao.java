package app.persistence.account;

import app.model.account.Cuenta;
import app.model.account.Usuario;
import app.persistence.GenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class CuentaDao extends GenericDao<Cuenta> {

    @Override
    protected Class getDomainClass() {
        return Cuenta.class;
    }

    public Cuenta getByUsuarioEmail(String email) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        // Uso de criteria
        CriteriaQuery<Cuenta> cq = cb.createQuery(Cuenta.class);
        // Creamos el root: select * from ENTIDAD
        Root<Cuenta> cuenta= cq.from(Cuenta.class);
        // Join
        Join<Cuenta,Usuario> usuario = cuenta.join("usuario", JoinType.LEFT);
        // Clausula Where
        cq.where(cb.equal(usuario.get("email"), email));
        cq.select(cuenta);
        List<Cuenta> cuentas = this.entityManager.createQuery(cq).getResultList();

        if (cuentas.size() == 0){
//            throw Exception();
        }

        return cuentas.get(0);
    }
}
