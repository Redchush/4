package catalog.domain.mediation;

/**
 * Created by Student on 31-May-16.
 */
public enum Criteria {
    CATEGORY(0), SUBCATEGOTY(1), NAME(2), PROVIDER(3), DATE_OF_ISSUE(4), BODY(5);

    private int num;

    Criteria(int num) {
        this.num = num;
    }
    public static Criteria getCriteriaByCode(int code){
        for (Criteria criteria : Criteria.values()){
            if (criteria.num == code)
                return criteria;
        }
        return null;
    }





   /*  public static final int CATEGORY = 0;
    public static final int SUBCATEGOTY = 1;
    public static final int NAME = 2;
    public static final int BODY = 3;
    public static final int EXIT = 4;

    provider>http://www.tele.ru/</provider>
                <dateOfIssue>2014-11-12</dateOfIssue>
                <body>

    */
}
