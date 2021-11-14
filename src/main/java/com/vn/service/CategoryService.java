package com.vn.service;

import com.vn.Utils;
import com.vn.dto.CategoryDto;
import com.vn.model.Category;
import com.vn.repository.CategoryRepository;
import org.modelmapper.ModelMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
            if (!categoryRepository.insertCategory(category)) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            request.setAttribute("message", "Thêm loai thất bại");
            return false;
        }
        return true;
    }

    public void deleteCategory(HttpServletRequest request) {
        categoryRepository.deleteCategory(request.getParameter("categoryId"));
    }

    public Category findById(String id) {
        return categoryRepository.findById(id);
    }

    public boolean updateCategory(HttpServletRequest request) {
        try {
            CategoryDto categoryDto = Utils.categoryRequestToDto(request);
            Category category = categoryRepository.findById(categoryDto.getCategoryId());
            if (category != null && categoryRepository.updateCategory(mapper.map(categoryDto, Category.class))) {
                return true;
            }
        } catch (Exception exception) {
            request.setAttribute("message", "Sửa loai thất bại");
        }
        return false;
    }
}
