package com.vn.service;

import com.vn.Utils;
import com.vn.constant.Constant;
import com.vn.model.Product;
import com.vn.repository.ProductRepository;
import org.modelmapper.ModelMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.util.Date;
import java.util.List;

import static org.modelmapper.convention.MatchingStrategies.STANDARD;

public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public ProductService() {
        productRepository = new ProductRepository();
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STANDARD);
    }

    public List<Product> findAll(int total) {
        return productRepository.findAll(total);
    }

    public Product findById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> findByCategoryId(String id, int total) {
        return productRepository.findByCategoryId(id, total);
    }

    public boolean insertProduct(HttpServletRequest request) {
        try {
            Product product = mapper.map(Utils.ProductRequestToDto(request), Product.class);
            product.setImage(saveImageProduct(request));
            if (!productRepository.insertProduct(product)) {
                // DELETE IMAGE
                String pathImage = request.getServletContext().getRealPath("") +
                        Constant.PATH_IMAGE_PRODUCT + product.getImage().substring(product.getImage().indexOf("/") + 1);
                File file = new File(pathImage);
                file.deleteOnExit();
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            request.setAttribute("message", "thêm sản phẩm thất bại");
            return false;
        }
    }

    public boolean updateProduct(HttpServletRequest request) {
        try {
            File file;
            Product product = mapper.map(Utils.ProductRequestToDto(request), Product.class);
            Product db = productRepository.findById(product.getProductId());
            String imageOld = request.getServletContext().getRealPath("") +
                    Constant.PATH_IMAGE_PRODUCT + db.getImage().substring(db.getImage().indexOf("/") + 1);
            product.setImage(saveImageProduct(request));
            if (!productRepository.updateProduct(product)) {
                String pathImage = request.getServletContext().getRealPath("") +
                        Constant.PATH_IMAGE_PRODUCT + product.getImage().substring(product.getImage().indexOf("/") + 1);
                file = new File(pathImage);
                file.deleteOnExit();
                throw new Exception();
            }
            file = new File(imageOld);
            file.deleteOnExit();
            return true;
        } catch (Exception e) {
            request.setAttribute("message", "sửa sản phẩm thất bại");
            return false;
        }
    }

    private String saveImageProduct(HttpServletRequest request) {
        String uploadPath = request.getServletContext().getRealPath("") +
                Constant.PATH_IMAGE_PRODUCT;
        try {
            Part part = request.getPart("image");
            String header = part.getHeader("content-disposition");
            String name = new Date().getTime() + header
                    .substring(header.lastIndexOf("."), header.length() - 1);
            part.write(uploadPath + name);
            return "image_sach/" + name;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void deleteProduct(HttpServletRequest request) {
        Product product = productRepository.findById(request.getParameter("id"));
        if (product != null) {
            productRepository.deleteProduct(product.getProductId());
            String pathImage = request.getServletContext().getRealPath("") +
                    Constant.PATH_IMAGE_PRODUCT + product.getImage().substring(product.getImage().indexOf("/") + 1);
            File file = new File(pathImage);
            file.deleteOnExit();
        }
    }
}
