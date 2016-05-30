package pl.aptewicz.allegrochecker.converter;

import org.springframework.stereotype.Service;

import pl.allegro.webapi.CategoryTreeType;
import pl.aptewicz.allegrochecker.model.Category;

@Service
public class CategoryConverter implements CategoryConverterInterface {

	@Override
	public Category convert(CategoryTreeType categoryTreeType) {
		return new Category(categoryTreeType.getCategoryName(),
				categoryTreeType.getCategoryId(),
				categoryTreeType.getCategoryParentId(),
				categoryTreeType.getCategoryItemsCount());
	}

}
