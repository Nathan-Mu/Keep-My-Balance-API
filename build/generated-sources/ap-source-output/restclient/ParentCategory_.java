package restclient;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import restclient.ChildCategory;
import restclient.UserInfo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-10T00:20:55")
@StaticMetamodel(ParentCategory.class)
public class ParentCategory_ { 

    public static volatile SingularAttribute<ParentCategory, String> parentCategoryName;
    public static volatile CollectionAttribute<ParentCategory, ChildCategory> childCategoryCollection;
    public static volatile SingularAttribute<ParentCategory, Integer> parentCategoryId;
    public static volatile SingularAttribute<ParentCategory, UserInfo> userId;

}