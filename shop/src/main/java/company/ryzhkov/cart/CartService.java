package company.ryzhkov.cart;

import company.ryzhkov.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Stateful
@SessionScoped
@NoArgsConstructor
public class CartService implements Serializable {
    private Map<Product, Integer> map;

    @PostConstruct
    public void init() {
        map = new HashMap<>();
    }

    public void addToCart(Product product, Integer number) {
        Integer n = map.put(product, number);
        if (n != null) map.put(product, n + number);
    }
}
