package catalog.domain.entity;

import catalog.domain.exeption.DomainException;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by user on 29.05.2016.
 */

@XmlRootElement(name = "catalog")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "catalogType",propOrder = {"name", "categories"})

public class Catalog implements CatalogEntity {

    @XmlElement(name = "categotyName")
    private List<Category> categories = new ArrayList<>();

    @XmlAttribute(name = "name", required = true)
    private String name;

    public Catalog(List<Category> newsList) {
        this.categories = newsList;
    }

    public Catalog() {
    }

    public String getName() {
        return name;
    }

    @XmlTransient
    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    @XmlTransient
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addNews(Category category) throws DomainException {
        try{
            categories.add(category);
        }catch (Exception ex){
            if (ex.getCause().equals(NullPointerException.class))
                throw new DomainException("Can't add to category list null value", ex);
            else
                throw new DomainException("Can't add to category list this value", ex);
        }
    }
    public void removeNews(Category category) throws DomainException{
        try{
            categories.remove(category);
        }catch (Exception ex){
            if (ex.getCause().equals(NullPointerException.class))
                throw new DomainException("Can't remove from category list null value", ex);
            else
                throw new DomainException("Can't remove from category list this value", ex);
        }
    }
    public void addAllNews(Collection<Category> categoryList) throws DomainException {
        try{
            categories.addAll(categoryList);
        }catch (Exception ex){
            throw new DomainException("Can't add at category list this collection", ex);
        }
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "categories=" + categories +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Catalog catalog = (Catalog) o;

        if (categories != null ? !categories.equals(catalog.categories) : catalog.categories != null) return false;
        return name != null ? name.equals(catalog.name) : catalog.name == null;

    }

    @Override
    public int hashCode() {
        int result = categories != null ? categories.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
