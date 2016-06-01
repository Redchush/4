package catalog.domain.entity;

import catalog.domain.exeption.DomainException;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by user on 29.05.2016.
 */
@XmlRootElement(name = "subcategoryName")
@XmlType(name = "subcategoryNameType",propOrder = {"news"})
@XmlAccessorType(XmlAccessType.FIELD)

public class Subcategory implements CatalogEntity{

    @XmlAttribute(name = "name", required = true)
    private String name;

    @XmlElement(name = "news")
    private List<News> news= new ArrayList<>();;

    public Subcategory(String name, List<News> newsList) {
        this.news = newsList;
        this.name = name;
    }

    public Subcategory() {
    }

    public Subcategory(String subcategoryName) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @XmlTransient
    public void setName(String name) {
        this.name = name;
    }

    public List<News> getNewsList() {
        return news;
    }

    @XmlTransient
    public void setNewsList(List<News> newsList) {
        this.news = newsList;
    }

    public void addNews(News newNews) throws DomainException {
       try{
            news.add(newNews);
        }catch (Exception ex){
           if (ex.getCause().equals(NullPointerException.class))
            throw new DomainException("Can't add to news list null value", ex);
            else
            throw new DomainException("Can't add to news list this value", ex);
        }
    }
    public void removeNews(News oldNews) throws DomainException{
        try{
            news.remove(oldNews);
        }catch (Exception ex){
            if (ex.getCause().equals(NullPointerException.class))
                throw new DomainException("Can't remove from news list null value", ex);
            else
                throw new DomainException("Can't remove from news list this value", ex);
        }
    }
    public void addAllNews(Collection<News> list) throws DomainException {
        try{
            news.addAll(list);
        }catch (Exception ex){
            throw new DomainException("Can't add at news list this collection", ex);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subcategory that = (Subcategory) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return news != null ? news.equals(that.news) : that.news == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (news != null ? news.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Subcategory{" +
                "name='" + name + '\'' +
                ", newsList=" + news +
                '}' + "\n";
    }
}
