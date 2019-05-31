package app.model;

import app.model.event.*;
import app.model.event.EnumTipos.TipoModalidad;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class NewTemplate {

    private Template template;
    private Date fechaLimite;
    private TipoModalidad tipoModalidad;
    private List<Item> items;
}
