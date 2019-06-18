package app.controller;

import app.model.web.ApiResponse;
import app.model.event.Item;
import app.service.event.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(value = {"app/item"})
@EnableAutoConfiguration
public class ItemController {

    @Autowired
    private ItemService itemService;

    /*
    * Retorna todos los items.
    */
    @GetMapping("/all")
    public ApiResponse<List<Item>> getAllEventos() {
        List<Item> items = this.itemService.getAllItems();
        return new ApiResponse<List<Item>>(HttpStatus.OK.value(), "Todos los items.", items);
    }

    /*
    * Crea un item.
    */
    @PostMapping("/")
    public ApiResponse<?> nuevoItem(@RequestBody Item nuevoItem) {
        Item item = this.itemService.createNuevoItem(nuevoItem);
        return new ApiResponse<Item>(HttpStatus.CREATED.value(),"Nuevo item creado.",item);
    }
}
