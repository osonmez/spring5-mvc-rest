package guru.springfamework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springfamework.domain.Category;

/**
 * Created by jt on 9/24/17.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);
}
