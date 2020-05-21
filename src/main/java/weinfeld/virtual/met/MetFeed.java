package weinfeld.virtual.met;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MetFeed {

    List<Department> departments;

    int total;
    List<Integer> objectIDs;

    int objectID;
    String primaryImage;
    String objectName;
    String culture;
    String period;
    String objectDate;

    class Department {
        int departmentId;
        String displayName;
    }


}
