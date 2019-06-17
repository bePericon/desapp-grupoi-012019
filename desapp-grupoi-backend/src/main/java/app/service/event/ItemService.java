package app.service.event;

import app.error.exception.ExceptionNoContent;
import app.model.event.Evento;
import app.model.event.Item;
import app.persistence.event.ItemDao;
import app.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemService extends GenericService<Evento> {

    @Autowired
    private ItemDao dao;

    @Override
    protected ItemDao getDao() {
        return this.dao;
    }

    /*
     * Retorna todos los items disponibles.
     */
    public List<Item> getAllItems() {
        List<Item> items = this.getDao().getAllItemsDisponibles();
        if (items.isEmpty()) {
            throw new ExceptionNoContent("Lista de items vacia.");
        }
        return items;
    }

    
//    guarda el item
//    public Usuario guardarItem(Item item) {
//        if(this.yaExiste(nuevoUsuario))
//            throw new ExceptionConflict("Ya existe un usuario con email: " +nuevoUsuario.getEmail());

//        if(!this.esValido(nuevoUsuario))
//            throw new ExceptionBadRequest("Los datos del nuevo usuario no son validos.");

//        String pass = this.bCrypt.encode(nuevoUsuario.getContrasenia());
//        nuevoUsuario.setContrasenia(pass);

//        this.save(Item.build(item));
//
//        Usuario usuario = this.getDao().getByEmail(nuevoUsuario.getEmail());
//        Cuenta cuenta = new Cuenta(usuario);
//        this.cuentaService.save(cuenta);
//
//        return usuario;
//    }
}
