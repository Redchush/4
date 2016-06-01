package old_realiz.impl;

/**
 * Created by user on 01.06.2016.
 */

import catalog.dao.IDao;
import catalog.dao.exception.DAOException;
import catalog.domain.entity.Catalog;
import catalog.domain.entity.News;
import catalog.domain.exeption.DomainException;

import javax.xml.bind.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

class DAOImpl implements IDao {

    private final URL path = getClass().getResource("../../../catalog/source/Catalog.xml");
    private final URL path1 = getClass().getResource("../../../catalog/source/NewsCatalog.xml");
    private Unmarshaller unmarshaller;
    private Marshaller marshaller;

    public DAOImpl(){
        unmarshaller = defineUnmarshaller(path);
        marshaller = defineMarshaller(path);
    }

    @Override
    public void saveNews(News news) throws DAOException {
        try {
            marshaller = defineMarshaller(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
            File in = new File(path1.getPath());
            System.out.println(marshaller);
            //marshaller.marshal(news, in);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void saveNews(News news, Catalog catalog) throws DAOException {
        try {
            marshaller = defineMarshaller(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
            File in = new File(path.getPath());
            System.out.println(marshaller);
            marshaller.marshal(catalog, in);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Catalog getCatalog() throws DAOException {
        Catalog catalog = null;
        try {
            //Create instance of the JAXBContext from the class-name
//            JAXBContext jc;
//            jc = JAXBContext.newInstance(Class.forName(clazz.getName()));
//            Unmarshaller u = jc.createUnmarshaller();
//            resultObject = clazz.cast(u.unmarshal(queryResults));
            Class<?> clazz = Class.forName("catalog.domain.entity.Catalog");
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
   //         unmarshaller = jaxbContext.createUnmarshaller();

            unmarshaller =  JAXBContext.newInstance(clazz).createUnmarshaller();
        //    unmarshaller = defineUnmarshaller(path);

//            JAXBElement<Catalog> root = (JAXBElement<Catalog>) unmarshaller.unmarshal(path);
//            catalog = root.getValue();
 //              T<T> catalog = (Catalog) unmarshaller.unmarshal(path);

          Object result = unmarshaller.unmarshal(path);

          System.out.println(result);

        } catch (JAXBException e) {
            throw new DAOException("Unmarshalling failed ", e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  catalog;
    }

    private Unmarshaller defineUnmarshaller(URL path){
        Unmarshaller unmarshaller = null;
        JAXBContext jaxbContext = null;
        try {
            Class<?> clazz = Class.forName("catalog.domain.entity.Catalog");
            jaxbContext = JAXBContext.newInstance(clazz);
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return unmarshaller;
    }

    private Marshaller defineMarshaller(URL path) {
        Marshaller marshaller = null;
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Catalog.class);
            marshaller = jaxbContext.createMarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return marshaller;
    }

    public static void main(String[] args) throws MalformedURLException, DAOException, DomainException, ClassNotFoundException, JAXBException {
        System.gc();
        DAOImpl dao = new DAOImpl();
        Class<?> clazz = Class.forName("catalog.domain.entity.Catalog");
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        Object catalog =  unmarshaller.unmarshal(dao.getClass().getResource("../../../catalog/source/Catalog.xml"));
        System.out.println(catalog);

//        DAOImpl dao = new DAOImpl();
//        Catalog catalog = null;
//        try {
//            catalog = dao.getCatalog();
//            System.out.println(catalog);
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }

//        News news = new News("22", "name", new URL("http://www.tutoronline.ru/"), "date" , "body" );
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
//        dao.saveNews(news, catalog);
//
//        catalog = dao.getCatalog();
//
//        System.out.println();
//        System.out.println("||||| SECONDARY |||||");
//        System.out.println();
//        System.out.println(catalog);


    }
}

