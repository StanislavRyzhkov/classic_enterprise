package company.ryzhkov.repository;

import company.ryzhkov.entity.Category;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CategoryRepository extends AbstractRepository {

    public List<Category> getAll() {
        return em
                .createQuery("select c from Category c", Category.class)
                .getResultList()
                .stream()
                .sorted().collect(Collectors.toList());
    }
}
