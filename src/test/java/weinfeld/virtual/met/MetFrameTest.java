package weinfeld.virtual.met;

import org.junit.Test;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MetFrameTest {

    @Test
    public void requestDepartments() {
        //given
        MetController controller = mock(MetController.class);
        JComboBox<MetFeed.DepartmentList.Department> departmentComboBox = new JComboBox<>();
        BasicArrowButton nextButton = new BasicArrowButton(BasicArrowButton.EAST);
        BasicArrowButton previousButton = new BasicArrowButton(BasicArrowButton.WEST);


        //when
        MetFrame frame = new MetFrame(controller,departmentComboBox);

        //then
        verify(controller).requestDepartments();
    }


}