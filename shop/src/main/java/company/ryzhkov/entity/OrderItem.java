package company.ryzhkov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Product product;
    private Integer number;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OrderItem) {
            OrderItem orderItem = (OrderItem) obj;
            return this.product.equals(orderItem.getProduct());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.product);
    }
}
