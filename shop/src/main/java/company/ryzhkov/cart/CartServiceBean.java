package company.ryzhkov.cart;

import company.ryzhkov.api.CartService;
import company.ryzhkov.entity.Product;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Stateful
@SessionScoped
@NoArgsConstructor
public class CartServiceBean implements Serializable, CartService {
    private Map<Product, Integer> cartMap;

    @PostConstruct
    public void init() {
        cartMap = new HashMap<>();
    }

    @Override
    public void addToCart(Product product, Integer number) {
        Integer n = cartMap.put(product, number);
        if (n != null) cartMap.put(product, n + number);
    }

    @Override
    public int totalPrice() {
        return cartMap.entrySet().stream().mapToInt(e -> e.getKey().getPrice() * e.getValue()).sum();
    }

    @Override
    public Map<Product, Integer> getCart() {
        return cartMap;
    }
}
