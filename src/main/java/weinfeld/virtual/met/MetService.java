package weinfeld.virtual.met;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MetService {
    @GET("/public/collection/v1/departments")
    Call<MetFeed.DepartmentList>getDepartments();

    @GET("/public/collection/v1/objects")
    Call<MetFeed.DepartmentObjects>getObjectsInDepartment(@Query("departmentIds") String departmentId);

    @GET("/public/collection/v1/objects/{objectID}")
    Call<MetFeed.Object> getObjectMetadata(@Path("objectID") String objectID);
}
