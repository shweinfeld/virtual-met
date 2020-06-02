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
    public void getCallbackDepartments() {

        //given

        //when

        //then

    }

    @Test
    public void getCallbackDepObjects() {

        //given

        //when

        //then

    }

    @Test
    public void getCallbackObject() {

        //given

        //when

        //then
    }

    @Test
    public void onResponseDepartments() {

        //given
        MetService service = mock(MetService.class);
        JLabel label = mock(JLabel.class);
        BasicArrowButton arrow = mock(BasicArrowButton.class);
        JComboBox<MetFeed.DepartmentList.Department> comboBox = mock(JComboBox.class);
        MetController controller = new MetController(service, label, label, label, label, label, arrow, arrow, comboBox);
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
        BasicArrowButton arrow = mock(BasicArrowButton.class);
        JComboBox<MetFeed.DepartmentList.Department> comboBox = mock(JComboBox.class);
        MetController controller = new MetController(service, label, label, label, label, label, arrow, arrow, comboBox);
        ArrayList<Integer> objectIDs = mock(ArrayList.class);
        Call<MetFeed.DepartmentObjects> call = mock(Call.class);
        Response<MetFeed.DepartmentObjects> response = mock(Response.class);

        MetFeed.DepartmentObjects depObjects = new MetFeed.DepartmentObjects();
        ArrayList<Integer> objIds = new ArrayList<>();
        objIds.add(12345);

        depObjects.total = 1;
        depObjects.objectIDs = objIds;


        doReturn(depObjects).when(response).body();

        //when
        controller.getCallbackDepObjects().onResponse(call, response);

        //then
        verify(objectIDs).set(0, 12345);

    }

    @Test
    public void onResponseObjects() {

        //given
        MetService service = mock(MetService.class);
        JLabel label = mock(JLabel.class);
        BasicArrowButton arrow = mock(BasicArrowButton.class);
        JComboBox<MetFeed.DepartmentList.Department> comboBox = mock(JComboBox.class);
        MetController controller = new MetController(service, label, label, label, label, label, arrow, arrow, comboBox);
        Call<MetFeed.Object> call = mock(Call.class);
        Response<MetFeed.Object> response = mock(Response.class);

        MetFeed.Object obj = new MetFeed.Object();
        obj.objectName = "obj";
        obj.primaryImage = "";
        obj.culture = "culture";
        obj.objectDate = "date";
        obj.period = "period";

        doReturn(obj).when(response).body();

        //when
        controller.getCallbackObject().onResponse(call, response);

        //then
        verify(label).setText(obj.objectName);
        verify(label).setText("No image to display");
        verify(label).setText(obj.culture);
        verify(label).setText(obj.objectDate);
        verify(label).setText(obj.period);

    }

}