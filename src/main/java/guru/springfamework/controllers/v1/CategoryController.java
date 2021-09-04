package guru.springfamework.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CategoryListDTO;
import guru.springfamework.services.CategoryService;

//@Controller
//@RequestMapping("${some.url.value}")
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {
	
	public static final String BASE_URL = "/api/v1/categories";
	
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public CategoryListDTO getAllCategories(){
		return new CategoryListDTO(categoryService.getAllCategories());
	}
	
	@GetMapping("/{name}")
	@ResponseStatus(code = HttpStatus.OK)
	public CategoryDTO getCategoryByName(@PathVariable String name){
		return categoryService.getCategoryByName(name);
	}

}
