package ru.bve.notes.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.bve.notes.Repositories.CategoryRepository;
import ru.bve.notes.domain.CategoryEntity;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CategoryView {
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = {"/category"}, method = RequestMethod.GET)
    public String getIndex(Model model){
        Map<Long, CategoryEntity> categories = getCategories();

        model.addAttribute("categories", categories.values());

        return "category";
    }

    @RequestMapping(value = {"/category/{id}"}, method = RequestMethod.GET)
    public String getIndex(Model model, @PathVariable long id){
        Map<Long, CategoryEntity> categories = getCategories();

        model.addAttribute("categories", categories.values());
        model.addAttribute("currentCategory", categories.get(null));

        return "category";
    }

    private Map<Long, CategoryEntity> getCategories(){
        Map<Long, CategoryEntity> result = new HashMap<>();
        Iterable<CategoryEntity> categories = categoryRepository.findAll();

        for (CategoryEntity entity: categories){
            result.put(entity.getId(), entity);
        }
        return result;
    }
}
