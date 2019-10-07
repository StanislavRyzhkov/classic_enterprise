package company.ryzhkov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "vendor_code", nullable = false)
    private String vendorCode;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "created")
    private Date created;

    @Column(name = "picture")
    private String picture;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product product = (Product) obj;
            return product.getVendorCode().equals(((Product) obj).getVendorCode());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.vendorCode);
    }
}
