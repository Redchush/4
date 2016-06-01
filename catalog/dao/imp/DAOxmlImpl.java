package catalog.dao.imp;

import catalog.dao.IDao;
import catalog.dao.exception.DAOException;
import catalog.domain.entity.*;
import catalog.domain.exeption.DomainException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 01.06.2016.
 */
public class DAOxmlImpl implements IDao {
    private final URL resource = getClass().getResource("../../../catalog/source/NewsCatalog.xml");
    private final String ERROR_MSG = "Catalog is unavailiable ";
    JAXBContext jaxbContext;
    {
        try {
            jaxbContext = JAXBContext.newInstance(Catalog.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    private Unmarshaller unmarshaller;
    private Marshaller marshaller;

    public DAOxmlImpl(){
        unmarshaller = defineUnmarshaller(resource);
        marshaller = defineMarshaller(resource);
    }

    @Override
    public void saveNews(News news) throws DAOException {
        File in = new File(resource.getPath());
        Catalog catalog =getCatalog();

        try {
            List<News> sub = new ArrayList<>();
            Category category = CatologLogic.hasCategory(news.getCategoryName());
            Subcategory subcategory = CatologLogic.hasSubcategory(news.getSubcategoryName());
            news.setId(news.getCategoryName().charAt(0) +
                    "_" + news.getSubcategoryName().charAt(0) +
                    "_"+ subcategory.getNewsList().size());

            subcategory.addNews(news);
            category.addSubcategoriy(subcategory);
            catalog.addNews(category);

        } catch (DomainException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("||||| THIRD |||||");
        System.out.println();
        System.out.println(catalog);

        try {
            marshaller.marshal(catalog, in);
        } catch (JAXBException e) {
            throw new DAOException("Catalog is unavailiable ");
        }
    }

    private void saveNews(News news, Catalog catalog) throws DAOException, JAXBException, FileNotFoundException {
        File file = new File(resource.getPath());
        marshaller.marshal(getCatalog(), file);
    }

    @Override
    public Catalog getCatalog() throws DAOException {
        Catalog catalog = null;
        try {
             catalog = (Catalog) unmarshaller.unmarshal(resource);
             CatologLogic.setCatalog(catalog);
             CatologLogic.setSubcategories();
         } catch (JAXBException e) {
            throw new DAOException(ERROR_MSG, e);
        }
        return catalog;
    }

    private Unmarshaller defineUnmarshaller(URL path){
        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return unmarshaller;
    }

    private Marshaller defineMarshaller(URL path) {
        Marshaller marshaller = null;
        try {
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return marshaller;
    }

//    public static void main(String[] args) throws MalformedURLException, DAOException, DomainException, ClassNotFoundException, JAXBException {
//        DAOxmlImpl dao = new DAOxmlImpl();
//        Catalog catalog = dao.getCatalog();
//        System.out.println(catalog);
//
//        News news = new News("999", "name", new URL("http://www.tutoronline.ru/"), "date" , "body" );
//        List<News> sub = new ArrayList<>();
//        sub.add(news);
//        Subcategory subcategory = new Subcategory("Новая", sub);
//        List<Subcategory> cat = new ArrayList<>();
//        cat.add(subcategory);
//        Category category = new Category("Категория", cat );
//        if (catalog != null) {
//           catalog.addNews(category);
//        }
//
//        try {
//            dao.saveNews(news, catalog);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        catalog = dao.getCatalog();
//        System.out.println();
//        System.out.println("||||| THIRD |||||");
//        System.out.println();
//        System.out.println(catalog);
//    }
}

