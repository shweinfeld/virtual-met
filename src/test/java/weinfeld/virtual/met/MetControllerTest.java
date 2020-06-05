package weinfeld.virtual.met;

import org.junit.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MetControllerTest {

    @Test
    public void requestDepartments() {

        //given
        MetService service = mock(MetService.class);
        JLabel label = mock(JLabel.class);
        JButton arrow = mock(JButton.class);
        JComboBox<MetFeed.DepartmentList.Department> comboBox = mock(JComboBox.class);
        MetController controller = new MetController(service, label, label, label, label, label, label, arrow, arrow, comboBox);
        Call<MetFeed.DepartmentList> call = mock(Call.class);
        doReturn(call).when(service).getDepartments();

        //when
        controller.requestDepartments();

        //then
        verify(service).getDepartments();
        verify(service.getDepartments()).enqueue(any());
    }

    @Test
    public void requestObjects() {

        //given
        MetService service = mock(MetService.class);
        JLabel label = mock(JLabel.class);
        JButton arrow = mock(JButton.class);
        JComboBox<MetFeed.DepartmentList.Department> comboBox = mock(JComboBox.class);
        MetController controller = new MetController(service, label, label, label, label, label, label, arrow, arrow, comboBox);
        Call<MetFeed.DepartmentObjects> call = mock(Call.class);
        doReturn(call).when(service).getObjectsInDepartment(1);

        //when
        controller.requestObjects(1);

        //then
        verify(service).getObjectsInDepartment(1);
        verify(service.getObjectsInDepartment(1)).enqueue(any());

    }

    @Test
    public void requestObjectData() {

        //given
        MetService service = mock(MetService.class);
        JLabel label = mock(JLabel.class);
        JButton arrow = mock(JButton.class);
        JComboBox<MetFeed.DepartmentList.Department> comboBox = mock(JComboBox.class);
        MetController controller = new MetController(service, label, label, label, label, label, label, arrow, arrow, comboBox);
        Call<MetFeed.Object> call = mock(Call.class);
        ArrayList<Integer> objectIDs = mock(ArrayList.class);
        controller.objectIDs = objectIDs;
        doReturn(call).when(service).getObjectMetadata(1);
        doReturn(5).when(controller.objectIDs).size();
        doReturn(1).when(controller.objectIDs).get(1);

        //when
        controller.requestObjectData(1);

        //then
        verify(service).getObjectMetadata(1);
        verify(service.getObjectMetadata(1)).enqueue(any());
    }

    @Test
    public void onResponseDepartments() {

        //given
        MetService service = mock(MetService.class);
        JLabel label = mock(JLabel.class);
        JButton arrow = mock(JButton.class);
        JComboBox<MetFeed.DepartmentList.Department> comboBox = mock(JComboBox.class);
        MetController controller = new MetController(service, label, label, label, label, label, label, arrow, arrow, comboBox);
        Call<MetFeed.DepartmentList> call = mock(Call.class);
        Response<MetFeed.DepartmentList> response = mock(Response.class);

        MetFeed.DepartmentList depList = new MetFeed.DepartmentList();

        MetFeed.DepartmentList.Department dep = new MetFeed.DepartmentList.Department();
        dep.displayName = "dep";
        dep.departmentId = 1;
        List<MetFeed.DepartmentList.Department> deps = new ArrayList<>();
        deps.add(dep);
        depList.departments = deps;


        doReturn(depList).when(response).body();

        //when
        controller.getCallbackDepartments().onResponse(call, response);

        //then
        verify(comboBox).addItem(depList.departments.get(0));

    }

    @Test
    public void onResponseDepObjects() {

        //given
        MetService service = mock(MetService.class);
        JLabel label = mock(JLabel.class);
        JButton arrow = mock(JButton.class);
        JComboBox<MetFeed.DepartmentList.Department> comboBox = mock(JComboBox.class);
        MetController controller = new MetController(service, label, label, label, label, label, label, arrow, arrow, comboBox);
        Call<MetFeed.DepartmentObjects> call = mock(Call.class);
        Call<MetFeed.Object> objCall = mock(Call.class);
        Response<MetFeed.DepartmentObjects> response = mock(Response.class);

        MetFeed.DepartmentObjects depObjects = new MetFeed.DepartmentObjects();
        ArrayList<Integer> objIds = new ArrayList<>();
        objIds.add(12345);

        depObjects.total = 1;
        depObjects.objectIDs = objIds;
        doReturn(objCall).when(service).getObjectMetadata(depObjects.objectIDs.get(0));

        doReturn(depObjects).when(response).body();

        //when
        controller.getCallbackDepObjects().onResponse(call, response);

        //then
        assertEquals(controller.objectIDs, depObjects.objectIDs);

    }

    @Test
    public void onResponseObjects() {

        //given
        MetService service = mock(MetService.class);
        JLabel label = mock(JLabel.class);
        JButton arrow = mock(JButton.class);
        JComboBox<MetFeed.DepartmentList.Department> comboBox = mock(JComboBox.class);
        MetController controller = new MetController(service, label, label, label, label, label, label, arrow, arrow, comboBox);
        Call<MetFeed.Object> call = mock(Call.class);
        Response<MetFeed.Object> response = mock(Response.class);

        MetFeed.Object obj = new MetFeed.Object();
        obj.objectName = "obj";
        obj.primaryImage = "";
        obj.culture = "culture";
        obj.objectDate = "date";
        obj.period = "period";
        obj.title = "title";

        doReturn(obj).when(response).body();

        //when
        controller.getCallbackObject().onResponse(call, response);

        //then
        verify(label).setText(obj.objectName);
        verify(label).setText("No image to display");
        verify(label).setText(obj.culture);
        verify(label).setText(obj.objectDate);
        verify(label).setText(obj.period);
        verify(label).setText(obj.title);

    }

}