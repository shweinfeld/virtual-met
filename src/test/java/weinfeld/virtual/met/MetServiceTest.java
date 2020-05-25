package weinfeld.virtual.met;

import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MetServiceTest {

    @Test
    public void getDepartments() throws IOException {

        //given
        MetService service = new MetServiceFactory().getInstance();

        //when
        Response<MetFeed.DepartmentList> response = service.getDepartments().execute();

        //then
        assertTrue(response.toString(), response.isSuccessful());
        MetFeed.DepartmentList departmentList = response.body();
        assertNotNull(departmentList);

        List<MetFeed.DepartmentList.Department> departments = departmentList.departments;
        assertFalse(departments.isEmpty());
        MetFeed.DepartmentList.Department department = departments.get(0);
        assertNotNull(department.displayName);
        assert(department.departmentId > 0);

    }

    @Test
    public void getObjectsInDepartment() throws IOException {

        //given
        MetService service = new MetServiceFactory().getInstance();

        //when
        Response<MetFeed.DepartmentObjects> response = service.getObjectsInDepartment(18).execute();

        //then
        assertTrue(response.toString(), response.isSuccessful());
        MetFeed.DepartmentObjects departmentObjects = response.body();
        assertNotNull(departmentObjects);

        assert(departmentObjects.total > 0);
        ArrayList<Integer> objectIDs = departmentObjects.objectIDs;
        assertFalse(objectIDs.isEmpty());
        int objectID = objectIDs.get(0);
        assert(objectID > 0);

    }

    @Test
    public void getObjectMetadata() throws IOException {

        //given
        MetService service = new MetServiceFactory().getInstance();

        //when
        Response<MetFeed.Object> response = service.getObjectMetadata(50).execute();

        //then
        assertTrue(response.toString(), response.isSuccessful());
        MetFeed.Object object = response.body();
        assertNotNull(object);

        assertNotNull(object.objectName);
        assertNotNull(object.objectDate);
        assertNotNull(object.period);
        assertNotNull(object.culture);
        assertNotNull(object.primaryImage);

        System.out.println(object.objectName);
        System.out.println(object.objectDate);
        System.out.println(object.primaryImage);

    }
}