package com.vn.service;

import com.vn.Utils;
import com.vn.dto.CategoryDto;
import com.vn.model.Category;
import com.vn.repository.CategoryRepository;
import org.modelmapper.ModelMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

public class CategoryService {
    private CategoryRepository categoryRepository = new CategoryRepository();
    private ModelMapper mapper = new ModelMapper();

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public boolean insertCategory(HttpServletRequest request) {
        try {
            CategoryDto dto = Utils.categoryRequestToDto(request);
            Category category = mapper.map(dto, Category.class);
            category.setCategoryId(UUID.randomUUID().toString());
            if (!categoryRepository.insertCategory(category)) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            request.setAttribute("messsage", "Them loai that bai");
            return false;
        }
        return true;
    }

    public void deleteCategory(HttpServletRequest request) {
        categoryRepository.deleteCategory(request.getParameter("categoryId"));
    }
}
