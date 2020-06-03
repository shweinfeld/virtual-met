package weinfeld.virtual.met;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MetFrame extends JFrame {

    int index;
    public MetFrame(MetController controller,
                    JComboBox<MetFeed.DepartmentList.Department> departmentComboBox,
                    JLabel objectImage,
                    JLabel objectTitle,
                    JLabel objectName,
                    JLabel objectDate,
                    JLabel objectPeriod,
                    JLabel objectCulture,
                    JButton nextButton,
                    JButton previousButton) {

        JPanel departmentPanel = new JPanel();
        JPanel objectPanel = new JPanel();
        JPanel arrowPanel = new JPanel();
        setSize(600, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Virtual Met");
        setLayout(new BorderLayout());

        departmentPanel.setLayout(new FlowLayout());
        departmentComboBox.isEditable();
        departmentPanel.add(departmentComboBox);
        add(departmentPanel, BorderLayout.WEST);

        departmentComboBox.addActionListener(ActionEvent -> {getDepObjects(controller, departmentComboBox);});


        objectPanel.setLayout(new BoxLayout(objectPanel, BoxLayout.Y_AXIS));

        objectPanel.add(objectTitle);
        objectPanel.add(objectName);
        objectPanel.add(objectCulture);
        objectPanel.add(objectDate);
        objectPanel.add(objectPeriod);
        objectPanel.add(objectImage);

        arrowPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        previousButton.addActionListener(ActionEvent -> {getPreviousObject(controller);});
        nextButton.addActionListener(ActionEvent -> {getNextObject(controller);});


        arrowPanel.add(previousButton);
        arrowPanel.add(nextButton);

        add(arrowPanel, BorderLayout.SOUTH);

        add(objectPanel, BorderLayout.CENTER);

        controller.requestDepartments();


    }

    private void getPreviousObject(MetController controller) {
        index --;
        controller.requestObjectData(index);
    }

    private void getNextObject(MetController controller) {
        index ++;
        controller.requestObjectData(index);

    }


    private void getDepObjects(MetController controller, JComboBox<MetFeed.DepartmentList.Department> departmentComboBox) {
        MetFeed.DepartmentList.Department selectedDep = (MetFeed.DepartmentList.Department) departmentComboBox.getSelectedItem();
        int depId = selectedDep.departmentId;
        index = 0;
        controller.requestObjects(depId);


    }


    public static void main(String[] args) {

        MetService service = new MetServiceFactory().getInstance();
        JComboBox<MetFeed.DepartmentList.Department> departmentComboBox = new JComboBox<>();
        JLabel objectTitle = new JLabel();
        JLabel objectImage = new JLabel();
        JLabel objectName = new JLabel();
        JLabel objectDate = new JLabel();
        JLabel objectPeriod = new JLabel();
        JLabel objectCulture = new JLabel();
        JButton nextButton = new JButton("\u2b9e");
        JButton previousButton = new JButton("\u2b9c");

        MetController controller = new MetController(service, objectImage, objectTitle, objectName, objectDate, objectPeriod, objectCulture, nextButton, previousButton, departmentComboBox);
        new MetFrame(controller,departmentComboBox, objectImage, objectTitle, objectName, objectDate, objectPeriod, objectCulture, nextButton, previousButton).setVisible(true);

    }

}
