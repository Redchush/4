package catalog.service.news.impl;

import catalog.dao.IDao;
import catalog.dao.exception.DAOException;
import catalog.dao.imp.DaoFactory;
import catalog.domain.entity.*;
import catalog.domain.mediation.Criteria;
import catalog.domain.mediation.Result;
import catalog.service.except.ServiceException;
import catalog.service.news.INewsService;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.NavigableMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static catalog.dao.imp.DaoFactory.xmlType;

/**
 * Created by user on 31.05.2016.
 */
public class INewsServiceImpl implements INewsService {

    public static final String ERROR = "Problem with accessing to DAO";
    IDao dao;
    public INewsServiceImpl() {
        DaoFactory factory = DaoFactory.getInstance();
        try {
            dao = factory.getDao(xmlType);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");



    @Override
    public void saveNewNews(NavigableMap<Criteria, String> criteria) throws ServiceException {
        News news = new News();
        for (Map.Entry<Criteria, String> element : criteria.entrySet()){
            Criteria crit = element.getKey();
            String value = element.getValue().trim();

            switch (crit){
                case CATEGORY :
                     news.setCategoryName(value);
                     break;
                case SUBCATEGOTY:
                    news.setSubcategoryName(value);
                    break;
                case NAME:
                    if (value.length() > 100){
                        throw new ServiceException("Name too logng");
                    }
                    news.setName(value);
                    break;
                case PROVIDER:
                    {
                        try {
                            news.setProvider(new URL(value));
                        } catch (MalformedURLException e) {
                            throw new ServiceException("Invalid provider type ", e);
                        }
                    }
                    break;
                case DATE_OF_ISSUE:
                    try {
                      simpleDateFormat.parse(value);
                    } catch (ParseException e) {
                       throw new ServiceException("Unvalid Date format");
                    }
                    news.setDateOfIssue(value);
                    break;
                case BODY:
                    news.setBody(value);
                    break;

            }

        }

        try {
            dao.saveNews(news);
        } catch (DAOException e) {
           throw new ServiceException("Failed to saveNewNews because of " + e, e);
        }
    }

    @Override
    public Result<News> findNews(NavigableMap<Criteria, String> criterias) throws ServiceException {
        Catalog catalog;
        Result<News> result = new Result<>(new ArrayList<>());
        System.out.println("In service");
        System.out.println(criterias);

        try {
            catalog = dao.getCatalog();
            Criteria criteria = criterias.firstKey();
            String value = criterias.get(criteria);
            Pattern pattern = makePattern(value);
            System.out.println(pattern);
            switch (criteria){
                case CATEGORY :
                    checkCategory(pattern ,result, catalog);
                    break;
                case SUBCATEGOTY:
                    checkSubCategory(pattern ,result, catalog);
                    break;
                case NAME:
                    checkName (pattern, result, catalog);
                    break;
                case PROVIDER:
                    checkProvider (pattern, result, catalog);
                    break;
                case DATE_OF_ISSUE:
                    checkDate (pattern, result, catalog);
                    break;
                case BODY:
                    checkBody(pattern, result, catalog);
                    break;
            }
        } catch (DAOException e) {
            throw  new ServiceException("Problem with accessing ", e);
        }
        System.out.println(result);
        return result;
    }

    private Pattern makePattern(String value) {
        StringBuilder builder = new StringBuilder(".*");
        char[] chars = new char[value.length()];
        for (char ch : value.toCharArray()){
            builder.append("[").append(Character.toUpperCase(ch))
                    .append(Character.toLowerCase(ch))
                    .append("]");
        }
        builder.append(".*");
        return Pattern.compile(builder.toString());

    }


    private void checkCategory(Pattern value, Result<News> result, Catalog catalog) {

        for (Category category : catalog.getCategories()){
            Matcher matcher = value.matcher(category.getName());
            if (matcher.matches()) {
                 result.addAll(CatologLogic.getAllNewsFromCategory(category));
            }
            System.out.println(category.getName() + " " + value);
        }
        System.out.println(result);
    }

    private void checkSubCategory(Pattern value, Result<News> result, Catalog catalog) {
        for (Subcategory sub : CatologLogic.getAllSubCategories()){
            Matcher matcher = value.matcher(sub.getName());
            if (matcher.matches()) {
                result.addAll(sub.getNewsList());
            }
        }
    }
    private void checkName(Pattern value, Result<News> result, Catalog catalog){
        for (News news : CatologLogic.getAllNews()){
            Matcher matcher = value.matcher(news.getName());
            if (matcher.matches()){
                result.add(news);
            }
        }
    }
    private void checkProvider(Pattern value, Result<News> result, Catalog catalog){
        for (News news : CatologLogic.getAllNews()){

            Matcher matcher = value.matcher(news.getProvider().getPath());
            if (matcher.matches()){
                result.add(news);
            }
        }
    }
    private void checkDate(Pattern value, Result<News> result, Catalog catalog){
        for (News news : CatologLogic.getAllNews()){

            Matcher matcher = value.matcher(news.getDateOfIssue());
            if (matcher.matches()){
                result.add(news);
            }
        }
    }
    private void checkBody(Pattern value, Result<News> result, Catalog catalog) {
        for (News news : CatologLogic.getAllNews()){
            Matcher matcher = value.matcher(news.getDateOfIssue());
            if (matcher.matches()){
                result.add(news);
            }
        }
    }



}
