package weinfeld.virtual.met;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MetFeed {

    class DepartmentList{
        List<Department> departments;
    }


    class DepartmentObjects {
        int total;
        List<Integer> objectIDs;
    }



    class Object {
        int objectID;
        String primaryImage;
        String objectName;
        String culture;
        String period;
        String objectDate;
    }


    class Department {
        int departmentId;
        String displayName;
    }


}
