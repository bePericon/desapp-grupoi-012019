package app.model.event;

import javax.persistence.*;

@Entity
@Table(name = "template_item")
public class TemplateItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Item item;

    private int cantidad;

    public TemplateItem() {
    }

    public TemplateItem(Item item, int cantidad) {
        this.item = item;
        this.cantidad = cantidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
