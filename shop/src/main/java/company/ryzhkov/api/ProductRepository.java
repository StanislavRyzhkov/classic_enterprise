package company.ryzhkov.api;

import company.ryzhkov.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> filterByCategoryName(String categoryName);

    Product getByVendorCode(String vendorCode);

    List<Product> getAll();
}
