package app.persistence.account;

import app.model.account.Cuenta;
import app.persistence.GenericDao;
import app.persistence.IGenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class CuentaDao extends GenericDao<Cuenta> implements IGenericDao<Cuenta> {

    @Override
    protected Class getDomainClass() {
        return Cuenta.class;
    }
}
