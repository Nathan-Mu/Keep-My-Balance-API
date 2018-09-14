package restclient;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import restclient.Account;
import restclient.ParentCategory;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-10T00:20:55")
@StaticMetamodel(UserInfo.class)
public class UserInfo_ { 

    public static volatile CollectionAttribute<UserInfo, Account> accountCollection;
    public static volatile SingularAttribute<UserInfo, String> password;
    public static volatile CollectionAttribute<UserInfo, ParentCategory> parentCategoryCollection;
    public static volatile SingularAttribute<UserInfo, Integer> userId;
    public static volatile SingularAttribute<UserInfo, String> username;

}