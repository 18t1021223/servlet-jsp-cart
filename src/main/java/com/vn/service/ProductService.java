package com.vn.service;

import com.vn.Utils;
import com.vn.constant.Constant;
import com.vn.model.Product;
import com.vn.repository.ProductRepository;
import org.modelmapper.ModelMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.modelmapper.convention.MatchingStrategies.STANDARD;

public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public ProductService() {
        productRepository = new ProductRepository();
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STANDARD);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> findByCategoryId(String id) {
        return productRepository.findByCategoryId(id);
    }

    public boolean insertProduct(HttpServletRequest request) {
        try {

            Product product = mapper.map(Utils.ProductRequestToDto(request), Product.class);
            product.setProductId(UUID.randomUUID().toString());
            product.setCreateDate(new Date());
            if (!productRepository.insertProduct(product)) {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            request.setAttribute("message", "Thêm sản phẩm thất bại");
            return false;
        }
    }

    private boolean saveImageProduct(HttpServletRequest request) {
        String uploadPath = request.getServletContext().getRealPath("") +
                Constant.PATH_IMAGE_PRODUCT +
                File.separator;
        try {

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
