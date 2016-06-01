package catalog.view.impl;

import catalog.domain.entity.News;
import catalog.domain.mediation.Criteria;
import catalog.domain.mediation.Request;
import catalog.domain.mediation.Response;
import catalog.domain.mediation.Result;
import catalog.view.View;

import java.util.*;

/**
 * Created by user on 01.06.2016.
 */
public class ConsoleView implements View {
    private static final String GREETING = "Hello, User!";
    private static final String CHOOSING_OPERATION = "Please, choose operation: FIND or SAVE";
    private static final String CHOOSING_CRITERIA;
    static {
        StringBuilder builder = new StringBuilder("Please, enter the criteria number to choose news\n");
        for (Criteria criteria : Criteria.values()){
            builder.append(criteria.toString()).append(" : ")
                    .append(criteria.ordinal()).append("\n");
        }
        CHOOSING_CRITERIA = new String(builder);
    }
    private static final String _EXIT_ = "EXIT";
    private static final int EXIT = 6;
    private static final String STATUS_MSG = "Status of your request : ";
    private static final String ENTERING_TEXT = "Please, enter the string to choose news\n";

//    private final List<String> msgList = Arrays.asList("Enter category", "Enter sucategory",
//            "Enter name", "Enter date", "Enter provider", "Enter body");

    private static final String ENTER = "Enter ";
    private static final String SAVE_NEW_NEWS = "SAVE_NEW_NEWS";
    private static final String FIND_NEWS = "FIND_NEWS";
    private static final String NOTE= "The date must be entered at fomat yyyy-mm-dd" +
                                       "The provider must be in URL format http://xxx.... .xx/ ";

    private static final String NAVIGATION = "Type N to next, type P to prev, " +
            "type ALL to show all.\n" +
            "Type EXIT to go to choosing " +
            "Amount of finded news : ";

    NavigableMap<Criteria, String> parameterMap;
    private String type;

    private boolean isFirst = true;


    public String start(){
        if (isFirst) {
            ConsoleHelper.writeMessage(GREETING);
            isFirst = false;
        }
        ConsoleHelper.writeMessage(CHOOSING_OPERATION + "6 TO EXIT");
        type = ConsoleHelper.readString(CriteriaType.VALIDATION_OPERATION);
        return type;
    }

    private void finding(){
        parameterMap = new TreeMap<>();
        ConsoleHelper.writeMessage(CHOOSING_CRITERIA);
        int result  = ConsoleHelper.readInt();
        Criteria criteria = Criteria.getCriteriaByCode(result);

        ConsoleHelper.writeMessage(ENTERING_TEXT);
        String msg = ConsoleHelper.readString(CriteriaType.VALIDATION_CRITERIA_TEXT);
        System.out.println(criteria + " "  ) ;
        parameterMap.put(criteria, msg);
    }

    private void saving(){
       ConsoleHelper.writeMessage(NOTE);
       parameterMap = new TreeMap<>();
       for (Criteria criteria : Criteria.values()){
           ConsoleHelper.writeMessage(ENTER + criteria);
           parameterMap.put(criteria, ConsoleHelper.readString());
       }
    }

    @Override
    public Request doUserAction() {
        String type = start();
        Request request = new Request();
        switch (type){
            case CriteriaType.FIND: finding();
                request.setCommandName(FIND_NEWS);
                break;
            case CriteriaType.SAVE : saving();
                request.setCommandName(SAVE_NEW_NEWS);
                break;
        }
        request.setParametersMap(parameterMap);
        return request;
    }


    @Override
    public void printAnswer(Response response) {

        switch (response.getIntStatus()){
            case Response.OK :
                    operateOkRequest(response);
            case Response.BAD_REQUEST:
                    operateBadRequest(response);
            case Response.DONE:
                     operateDoneRequest(response);
        }
    }

    @Override
    public void printAnswer(String message) {

    }

    private void operateDoneRequest(Response response) {
        ConsoleHelper.writeMessage(STATUS_MSG + "Done.");
    }

    private void operateBadRequest(Response response) {
        ConsoleHelper.writeMessage(STATUS_MSG + "Bad Request");
        ConsoleHelper.writeMessage(response.getMessage());
    }

    private void operateOkRequest(Response response) {
        Result<News> rezult = response.getResultSet();

        ConsoleHelper.writeMessage(STATUS_MSG + "OK");
        ConsoleHelper.writeMessage(NAVIGATION + rezult.size());

         while (true) {
             String s = ConsoleHelper.readString();
             if (s.equalsIgnoreCase("next")
                     || s.equalsIgnoreCase("N")){
                 if (rezult.hasNext()) {
                     ConsoleHelper.writeMessage(rezult.next().toString());
                     int remaining = rezult.size() - rezult.nextIndex();
                     ConsoleHelper.writeMessage("Amunt remaining : " + remaining);
                 }
             }
             if (s.equalsIgnoreCase("prev")
                     || s.equalsIgnoreCase("P")){
                 if (rezult.hasPrevious()) {
                     ConsoleHelper.writeMessage(rezult.previous().toString());
                 }
             }
             if (s.equalsIgnoreCase("all")){
                 for (News news : rezult.getList()){
                     ConsoleHelper.writeMessage(news.toString());
                 }
             }
             if(s.equals(_EXIT_)){
                 break;
             }
         }
    }
}
