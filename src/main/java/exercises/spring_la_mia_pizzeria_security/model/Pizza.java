package exercises.spring_la_mia_pizzeria_security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Required field")
    @Size(max = 40, message = "max 40 characters")
    private String name;

    @Lob
    @Size(max = 800, message = "max 800 characters")
    private String description;

    private String url;

    @DecimalMin(value = "0.01", message = "Min. price: €0.01")
    private double price;

    @OneToMany(mappedBy = "pizza")
    private List<PromoCode> promoCodes;

    @ManyToMany
    @JoinTable(name = "ingredient_pizza", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    // empty constructor
    public Pizza() {
    }

    // constructor * if I don't have this defined, I don't need the empty
    // constructor above bc it is automatically given by Java
    public Pizza(Integer id, String name, String description, String url, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.price = price;
    }

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<PromoCode> getPromoCodes() {
        return this.promoCodes;
    }

    public void setPromoCodes(List<PromoCode> promoCodes) {
        this.promoCodes = promoCodes;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    // toString
    @Override
    public String toString() {
        return String.format("%s : €%.2f", this.name, this.price);
    }

}
