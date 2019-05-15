package app.persistence.account;

import app.model.account.Cuenta;
import app.persistence.GenericDao;
import app.persistence.IGenericDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

@Repository
public class CuentaDao extends GenericDao<Cuenta> {

    @Override
    protected Class getDomainClass() {
        return Cuenta.class;
    }
}
