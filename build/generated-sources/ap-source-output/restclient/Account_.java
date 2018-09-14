package restclient;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import restclient.FundChange;
import restclient.UserInfo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-10T00:20:55")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, Integer> accountId;
    public static volatile SingularAttribute<Account, BigDecimal> balance;
    public static volatile CollectionAttribute<Account, FundChange> fundChangeCollection;
    public static volatile SingularAttribute<Account, String> accountType;
    public static volatile SingularAttribute<Account, UserInfo> userId;

}