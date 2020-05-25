package weinfeld.virtual.met;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MetController {

    private MetService service;

    JComboBox<MetFeed.DepartmentList.Department> departmentComboBox;

    private ArrayList<Integer> objectIDs;
    public MetController(MetService service, ArrayList<Integer> objectIDs) {
        this.service = service;
        this.objectIDs = objectIDs;

    }

    public void requestDepartments(JComboBox<MetFeed.DepartmentList.Department> departmentComboBox) {
        service.getDepartments().enqueue(new Callback<MetFeed.DepartmentList>() {
            @Override
            public void onResponse(Call<MetFeed.DepartmentList> call, Response<MetFeed.DepartmentList> response) {
                MetFeed.DepartmentList departmentList = response.body();
                List<MetFeed.DepartmentList.Department> departments = departmentList.departments;
                for (MetFeed.DepartmentList.Department department:departments) {
                    departmentComboBox.addItem(department);
                }
            }

            @Override
            public void onFailure(Call<MetFeed.DepartmentList> call, Throwable t) {

            }
        });
    }

    public void requestObjects(int depID) {
        service.getObjectsInDepartment(depID).enqueue(new Callback<MetFeed.DepartmentObjects>() {
            @Override
            public void onResponse(Call<MetFeed.DepartmentObjects> call, Response<MetFeed.DepartmentObjects> response) {
                MetFeed.DepartmentObjects departmentObjects = response.body();
                assert departmentObjects != null;
                objectIDs = departmentObjects.objectIDs;

            }

            @Override
            public void onFailure(Call<MetFeed.DepartmentObjects> call, Throwable t) {

            }
        });
    }

    public void requestObjectData(int objID,
                                  JLabel objectImage,
                                  JLabel objectName,
                                  JLabel objectDate,
                                  JLabel objectPeriod,
                                  JLabel objectCulture) {
        service.getObjectMetadata(objID).enqueue(new Callback<MetFeed.Object>() {
            @Override
            public void onResponse(Call<MetFeed.Object> call, Response<MetFeed.Object> response) {

                MetFeed.Object object = response.body();
                ImageIcon objectImg = new ImageIcon(object.primaryImage);
                objectImage.setIcon(objectImg);
                objectName.setText(object.objectName);
                objectDate.setText(object.objectDate);
                objectPeriod.setText(object.period);
                objectCulture.setText(object.culture);


            }

            @Override
            public void onFailure(Call<MetFeed.Object> call, Throwable t) {

            }
        });
    }
}
