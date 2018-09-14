package restclient;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import restclient.FundChange;
import restclient.ParentCategory;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-10T00:20:55")
@StaticMetamodel(ChildCategory.class)
public class ChildCategory_ { 

    public static volatile CollectionAttribute<ChildCategory, FundChange> fundChangeCollection;
    public static volatile SingularAttribute<ChildCategory, Integer> childCategoryId;
    public static volatile SingularAttribute<ChildCategory, String> childName;
    public static volatile SingularAttribute<ChildCategory, ParentCategory> parentCategoryId;

}