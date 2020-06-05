package weinfeld.virtual.met;

import org.junit.Test;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MetFrameTest {

    @Test
    public void requestDepartments() {
        //given
        MetController controller = mock(MetController.class);
        JComboBox<MetFeed.DepartmentList.Department> departmentComboBox = new JComboBox<>();
        JLabel objectImage = new JLabel();
        JLabel objectTitle = new JLabel();
        JLabel objectName = new JLabel();
        JLabel objectDate = new JLabel();
        JLabel objectPeriod = new JLabel();
        JLabel objectCulture = new JLabel();
        JButton nextButton = new JButton();
        JButton previousButton = new JButton();


        //when
        MetFrame frame = new MetFrame(controller,departmentComboBox, objectImage, objectTitle, objectName, objectDate, objectPeriod, objectCulture, nextButton, previousButton);

        //then
        verify(controller).requestDepartments();


    }


}