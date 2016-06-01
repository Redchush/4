package catalog.domain.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 01.06.2016.
 */
public class CatologLogic implements CatalogEntity {
    private static Catalog catalog;

    public static void setCatalog(Catalog currentCatalog){
        catalog = currentCatalog;
    }
    public static Subcategory getSubcategory(News news){
        return news.getSubcategory();
    }
    public static Category getCategoryByNewsId(News news){
        return news.getCategory();
    }
    public Category getCategotyByNewsId(String id){
       return null;
    }

    public static Category hasCategory(String name){
        for (Category category : catalog.getCategories())
        {
            if (category.getName().equals(name))
                return category;
        }
        return new Category(name);
    }

    public static Subcategory hasSubcategory(String subcategoryName) {
        for (Subcategory category : getAllSubCategories())
        {
            if (category.getName().equals(subcategoryName))
                return category;
        }
        return new Subcategory(subcategoryName);
    }
    public static void setSubcategories(){
        for (Category category : catalog.getCategories())
        {
            for (Subcategory subcategory : category.getSubcategoriesList()){
                    for (News news : subcategory.getNewsList()){
                        news.setCategory(category);
                        news.setSubcategory(subcategory);
                    }
            }
        }
    }
    public static List<News> getAllNewsFromCategory(Category category){
        List<News> newses = new ArrayList<>();
        for (Subcategory subcategory : category.getSubcategoriesList()) {
                    newses.addAll(subcategory.getNewsList());
        }
        return newses;
    }
    public static List<Subcategory> getAllSubCategories(){
        List<Subcategory> categories = new ArrayList<>();
        for (Category category : catalog.getCategories()) {
            categories.addAll(category.getSubcategoriesList());
        }
        return categories;
    }
    public static List<News> getAllNews(){
        List<News> newses = new ArrayList<>();
        for (Category category : catalog.getCategories())
        {
            newses.addAll(getAllNewsFromCategory(category));
        }
        return newses;
    }


}
