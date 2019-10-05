package company.ryzhkov.repository;

import company.ryzhkov.entity.Product;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ProductRepository extends AbstractRepository {

    public List<Product> getAll() {
        return em.createQuery("SELECT p from Product p ORDER BY p.created DESC", Product.class).getResultList();
    }
}
