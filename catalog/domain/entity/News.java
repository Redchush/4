package catalog.domain.entity;

import javax.xml.bind.annotation.*;
import java.net.URL;

/**
* Annotation Type XmlTransient prevents the mapping of a JavaBean property/type to XML representation.
* So with both fields and methods, there one of which marked by @XmlElement --> Class has two properties of the same name ...
*
**/

@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "newsType", propOrder = {"name", "provider",  "dateOfIssue" ,"body"})
@XmlType(name = "newsType", propOrder = {"name", "provider",  "dateOfIssue" ,"body"})
@XmlRootElement(name = "news")

public class News implements CatalogEntity{
    @XmlAttribute(name = "id", required = true)
    private String id;

    @XmlElement(name = "name", required = true)
    private String name;

    @XmlElement(name = "provider", required = true)
    private URL provider;

    @XmlElement(name = "dateOfIssue", required = true)
    private String dateOfIssue;

    @XmlElement(name = "body", required = true)
    private String body;

    @XmlTransient
    private Subcategory subcategory;

    @XmlTransient
    private Category category;

    @XmlTransient
    private String categoryName;

    @XmlTransient
    private String subcategoryName;
    /**
     *  @param id
     * @param name
     * @param provider
     * @param dateOfIssue
     * @param body
     */
    public News(String id, String name, URL provider, String dateOfIssue, String body) {
        this.id = id;
        this.body = body;
        this.dateOfIssue = dateOfIssue;
        this.name = name;
        this.provider = provider;
    }

    public News() {
    }

    public String getBody() {
        return body;
    }

    @XmlTransient
    public void setBody(String body) {
        this.body = body;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    @XmlTransient
    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getName() {
        return name;
    }

    @XmlTransient
    public void setName(String name) {
        this.name = name;
    }

    public URL getProvider() {
        return provider;
    }

    @XmlTransient
    public void setProvider(URL provider) {
        this.provider = provider;
    }

    public String getId() {
        return id;
    }

    @XmlTransient
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (id != null ? !id.equals(news.id) : news.id != null) return false;
        if (name != null ? !name.equals(news.name) : news.name != null) return false;
        if (provider != null ? !provider.equals(news.provider) : news.provider != null) return false;
        if (dateOfIssue != null ? !dateOfIssue.equals(news.dateOfIssue) : news.dateOfIssue != null) return false;
        return body != null ? body.equals(news.body) : news.body == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (provider != null ? provider.hashCode() : 0);
        result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", provider=" + provider +
                ", dateOfIssue=" + dateOfIssue +
                ", body='" + body + '\'' +
                '}' + "\n";
    }

    protected Subcategory getSubcategory() {
        return subcategory;
    }

    @XmlTransient
    protected void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    protected Category getCategory() {
        return category;
    }
    @XmlTransient
    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }
}
