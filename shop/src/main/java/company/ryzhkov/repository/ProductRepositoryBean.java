package company.ryzhkov.repository;

import company.ryzhkov.api.ProductRepository;
import company.ryzhkov.entity.Product;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ProductRepositoryBean extends AbstractRepositoryBean implements ProductRepository {

    @Override
    public List<Product> filterByCategoryName(String categoryName) {
        return em.createQuery("SELECT p FROM Product p WHERE p.category.name = :categoryName", Product.class)
                .setParameter("categoryName", categoryName)
                .getResultList();
    }

    @Override
    public Product getByVendorCode(String vendorCode) {
        return em.createQuery("SELECT p FROM Product p WHERE p.vendorCode = :vendorCode", Product.class)
                .setParameter("vendorCode", vendorCode)
                .getResultList()
                .stream().findFirst().orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return em.createQuery("SELECT p from Product p ORDER BY p.created DESC", Product.class).getResultList();
    }
}
