package restclient;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import restclient.Account;
import restclient.ChildCategory;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-10T00:20:55")
@StaticMetamodel(FundChange.class)
public class FundChange_ { 

    public static volatile SingularAttribute<FundChange, Account> accountId;
    public static volatile SingularAttribute<FundChange, Double> amount;
    public static volatile SingularAttribute<FundChange, String> locationName;
    public static volatile SingularAttribute<FundChange, String> changeType;
    public static volatile SingularAttribute<FundChange, ChildCategory> childCategoryId;
    public static volatile SingularAttribute<FundChange, Date> changeDate;
    public static volatile SingularAttribute<FundChange, Double> locationLatitude;
    public static volatile SingularAttribute<FundChange, Double> locationLongitude;
    public static volatile SingularAttribute<FundChange, String> description;
    public static volatile SingularAttribute<FundChange, Integer> changeId;

}