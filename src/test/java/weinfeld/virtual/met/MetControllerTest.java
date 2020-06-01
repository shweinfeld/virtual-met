package weinfeld.virtual.met;

import org.junit.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MetControllerTest {

    @Test
    public void getCallbackDepartments() {

        //given
        MetService service = mock(MetService.class);
        Call<MetFeed.DepartmentList> call = mock(Call.class);
        Response<MetFeed.DepartmentList> response = mock(Response.class);
        JLabel label = mock(JLabel.class);
        BasicArrowButton arrow = mock(BasicArrowButton.class);
        JComboBox<MetFeed.DepartmentList.Department> comboBox = mock(JComboBox.class);
        MetController controller = new MetController(service, label, label, label, label, label, arrow, arrow, comboBox);

        //when
        Callback<MetFeed.DepartmentList> callback = controller.getCallbackDepartments();

        //then
        verify(callback).onResponse(call, response);

    }

    @Test
    public void getCallbackDepObjects() {

        //given
        MetService service = mock(MetService.class);
        Call<MetFeed.DepartmentObjects> call = mock(Call.class);
        doReturn(call).when(service).getObjectsInDepartment(3);
        JLabel label = mock(JLabel.class);
        BasicArrowButton arrow = mock(BasicArrowButton.class);
        JComboBox<MetFeed.DepartmentList.Department> comboBox = mock(JComboBox.class);
        MetController controller = new MetController(service, label, label, label, label, label, arrow, arrow, comboBox);

        //when
        controller.requestObjects(3);

        //then
       // verify(call).enqueue(controller);

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

        List<MetFeed.DepartmentList.Department> deps = depList.departments;
        deps.add(dep);



        doReturn(deps).when(response).body();

        //when
        controller.getCallbackDepartments().onResponse(call, response);

        //then
        verify(comboBox).addItem(depList.departments.get(0));

    }
}