package weinfeld.virtual.met;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MetFeed {



    static class DepartmentList{
        List<Department> departments;

        static class Department {
            int departmentId;
            String displayName;

            @Override
            public String toString() {
                return displayName;
            }
        }

    }


    static class DepartmentObjects {
        int total;
        ArrayList<Integer> objectIDs;
    }



    static class Object {
        String primaryImage;
        String title;
        String objectName;
        String culture;
        String period;
        String objectDate;
    }





}
