package company.ryzhkov.api;

import company.ryzhkov.entity.Product;

import java.util.Map;

public interface CartService {

    void addToCart(Product product, Integer number);

    Map<Product, Integer> getCart();

    int totalPrice();
}
