package weinfeld.virtual.met;

import org.junit.Test;
import retrofit2.Call;
import retrofit2.Callback;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MetControllerTest {

    @Test
    public void requestDepartments() {

        //given
        MetService service = mock(MetService.class);
        Call<MetFeed.DepartmentList> call = mock(Call.class);
        doReturn(call).when(service).getDepartments();
        JLabel label = mock(JLabel.class);
        BasicArrowButton arrow = mock(BasicArrowButton.class);
        JComboBox<MetFeed.DepartmentList.Department> comboBox = mock(JComboBox.class);
        MetController controller = new MetController(service, label, label, label, label, label, arrow, arrow, comboBox);

        //when
        controller.requestDepartments();

        //then
        //verify(call).enqueue(controller);

    }

    @Test
    public void requestObjects() {

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
    public void requestObjectData() {

        //given

        //when

        //then
    }
}