package company.ryzhkov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category implements Comparable<Category> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Product> products;

    @Override
    public int compareTo(Category o) {
        return this.name.compareTo(o.getName());
    }
}
