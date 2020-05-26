package weinfeld.virtual.met;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MetFrame extends JFrame {

    int index;
    JPanel departmentPanel;
    JComboBox<MetFeed.DepartmentList.Department> departmentComboBox;
    JPanel objectPanel;
    JLabel objectImage;
    JLabel objectName;
    JLabel objectDate;
    JLabel objectPeriod;
    JLabel objectCulture;
    JPanel objectArrowPanel;
    BasicArrowButton nextButton;
    BasicArrowButton previousButton;
    MetService service;
    MetController controller;

    public MetFrame() {



        setSize(600, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Virtual Met");
        setLayout(new BorderLayout());

        departmentPanel = new JPanel();
        departmentPanel.setLayout(new FlowLayout());
        departmentComboBox = new JComboBox<>();
        departmentComboBox.isEditable();
        departmentPanel.add(departmentComboBox);
        add(departmentPanel, BorderLayout.WEST);

        departmentComboBox.addActionListener(ActionEvent -> {getDepObjects();});


        objectPanel = new JPanel();
        objectPanel.setLayout(new BoxLayout(objectPanel, BoxLayout.Y_AXIS));

        objectName = new JLabel();
        objectCulture = new JLabel();
        objectDate = new JLabel();
        objectPeriod = new JLabel();
        objectImage = new JLabel();
        objectPanel.add(objectName);
        objectPanel.add(objectCulture);
        objectPanel.add(objectDate);
        objectPanel.add(objectPeriod);
        objectPanel.add(objectImage);

        objectArrowPanel = new JPanel();
        objectArrowPanel.setLayout(new FlowLayout());
        previousButton = new BasicArrowButton(BasicArrowButton.WEST);
        previousButton.addActionListener(ActionEvent -> {getPreviousObject();});
        nextButton = new BasicArrowButton(BasicArrowButton.EAST);
        nextButton.addActionListener(ActionEvent -> {getNextObject();});

        objectArrowPanel.add(previousButton);
        objectArrowPanel.add(objectPanel);
        objectArrowPanel.add(nextButton);

        add(objectArrowPanel, BorderLayout.CENTER);
        service = new MetServiceFactory().getInstance();
        controller = new MetController(service, objectImage, objectName, objectDate, objectPeriod, objectCulture, nextButton, previousButton);
        controller.requestDepartments(departmentComboBox);


    }

    private void getPreviousObject() {
        index --;
        controller.requestObjectData(index);
    }

    private void getNextObject() {
        index ++;
        controller.requestObjectData(index);

    }


    private void getDepObjects() {
        MetFeed.DepartmentList.Department selectedDep = (MetFeed.DepartmentList.Department) departmentComboBox.getSelectedItem();
        int depId = selectedDep.departmentId;
        controller.requestObjects(depId);
        index = 0;

    }


    public static void main(String[] args) {
        new MetFrame().setVisible(true);
    }

}
