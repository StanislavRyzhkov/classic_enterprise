package company.ryzhkov.api;

import company.ryzhkov.entity.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> getAll();
}
