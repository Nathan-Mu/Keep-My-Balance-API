package restclient;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-10T00:20:55")
@StaticMetamodel(FundChangeText.class)
public class FundChangeText_ { 

    public static volatile SingularAttribute<FundChangeText, Integer> accountId;
    public static volatile SingularAttribute<FundChangeText, Double> amount;
    public static volatile SingularAttribute<FundChangeText, String> locationName;
    public static volatile SingularAttribute<FundChangeText, String> changeType;
    public static volatile SingularAttribute<FundChangeText, Integer> childCategoryId;
    public static volatile SingularAttribute<FundChangeText, Date> changeDate;
    public static volatile SingularAttribute<FundChangeText, Double> locationLatitude;
    public static volatile SingularAttribute<FundChangeText, Double> locationLongitude;
    public static volatile SingularAttribute<FundChangeText, String> description;
    public static volatile SingularAttribute<FundChangeText, Integer> changeId;
    public static volatile SingularAttribute<FundChangeText, Integer> userId;

}