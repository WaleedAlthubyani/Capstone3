package com.example.capstone3.Service;

import com.example.capstone3.DTO.CategoryDTO;
import com.example.capstone3.Model.Category;
import com.example.capstone3.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
//Mshari
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories(){
        return convertCategoriesToDTO(categoryRepository.findAll());
    }

    public List<CategoryDTO> convertCategoriesToDTO(List<Category> categories){
        List<CategoryDTO> categoriesDTO = new ArrayList<>();

        for (Category category : categories) {
            categoriesDTO.add(new CategoryDTO(category.getName(),category.getDescription()));
        }

        return categoriesDTO;
    }
}
