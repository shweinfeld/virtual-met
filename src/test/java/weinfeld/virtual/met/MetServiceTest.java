package weinfeld.virtual.met;

import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class MetServiceTest {

    @Test
    public void getDepartments() throws IOException {

        //given
        MetService service = new MetServiceFactory().getInstance();

        //when
        Response<MetFeed> response = service.getDepartments().execute();

        //then
        assertTrue(response.toString(), response.isSuccessful());
        MetFeed feed = response.body();
        assertNotNull(feed);

        List<MetFeed.Department> departments = feed.departments;
        assertFalse(departments.isEmpty());
        MetFeed.Department department = departments.get(0);
        assertNotNull(department.displayName);
        assert(department.departmentId < 0);

    }

    @Test
    public void getObjectsInDepartment() throws IOException {

        //given
        MetService service = new MetServiceFactory().getInstance();

        //when
        Response<MetFeed> response = service.getObjectsInDepartment("18").execute();

        //then
        assertTrue(response.toString(), response.isSuccessful());
        MetFeed feed = response.body();
        assertNotNull(feed);

        assert(feed.total > 0);
        List<Integer> objectIDs = feed.objectIDs;
        assertFalse(objectIDs.isEmpty());
        int objectID = objectIDs.get(0);
        assert(objectID > 0);

    }

    @Test
    public void getObjectMetadata() throws IOException {

        //given
        MetService service = new MetServiceFactory().getInstance();

        //when
        Response<MetFeed> response = service.getObjectMetadata("500895").execute();

        //then
        assertTrue(response.toString(), response.isSuccessful());
        MetFeed feed = response.body();
        assertNotNull(feed);

        assertNotNull(feed.objectName);
        assertNotNull(feed.objectDate);
        assertNotNull(feed.period);
        assertNotNull(feed.culture);
        assertNotNull(feed.primaryImage);

    }
}