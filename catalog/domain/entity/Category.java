package catalog.domain.entity;

import catalog.domain.exeption.DomainException;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by user on 29.05.2016.
 */
@XmlRootElement(name = "categotyName")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "categotyNameType",propOrder = {"subcategoriesList"})

public class Category implements CatalogEntity {

    @XmlAttribute(name = "name", required = true)
    private String name;

    @XmlElement(name = "subcategoryName")
    private List<Subcategory> subcategoriesList = new ArrayList<>();;

    public Category() {
    }

    public Category(String name, List<Subcategory> subcategoriesList) {
        this.name = name;
        this.subcategoriesList = subcategoriesList;
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @XmlTransient
    public void setName(String name) {
        this.name = name;
    }

    public List<Subcategory> getSubcategoriesList() {
        return subcategoriesList;
    }


    @XmlTransient
    public void setSubcategoriesList(List<Subcategory> subcategoriesList) {
        this.subcategoriesList = subcategoriesList;
    }

    public void addSubcategoriy(Subcategory news) throws DomainException {
        try{
            subcategoriesList.add(news);
        }catch (Exception ex){
            if (ex.getCause().equals(NullPointerException.class))
                throw new DomainException("Can't add to news list null value", ex);
            else
                throw new DomainException("Can't add to news list this value", ex);
        }
    }
    public void removeSubcategoriy(Subcategory news) throws DomainException{
        try{
            subcategoriesList.remove(news);
        }catch (Exception ex){
            if (ex.getCause().equals(NullPointerException.class))
                throw new DomainException("Can't remove from news list null value", ex);
            else
                throw new DomainException("Can't remove from news list this value", ex);
        }
    }
    public void addAllSubcategories(Collection<Subcategory> list) throws DomainException {
        try{
            subcategoriesList.addAll(list);
        }catch (Exception ex){
            throw new DomainException("Can't add at news list this collection", ex);
        }
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + "\n" + '\'' +
                ", subcategoriesList=" + subcategoriesList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        return subcategoriesList != null ? subcategoriesList.equals(category.subcategoriesList) : category.subcategoriesList == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (subcategoriesList != null ? subcategoriesList.hashCode() : 0);
        return result;
    }
}
