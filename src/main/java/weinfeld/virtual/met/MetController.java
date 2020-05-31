package weinfeld.virtual.met;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MetController {

    private MetService service;
    JLabel objectImage;
    JLabel objectName;
    JLabel objectDate;
    JLabel objectPeriod;
    JLabel objectCulture;
    BasicArrowButton nextButton;
    BasicArrowButton previousButton;
    JComboBox<MetFeed.DepartmentList.Department> departmentComboBox;


    private ArrayList<Integer> objectIDs;
    public MetController(MetService service, JLabel objectImage,
                         JLabel objectName,
                         JLabel objectDate,
                         JLabel objectPeriod,
                         JLabel objectCulture,
                         BasicArrowButton nextButton,
                         BasicArrowButton previousButton,
                         JComboBox<MetFeed.DepartmentList.Department> departmentComboBox) {
        this.service = service;
        this.objectImage = objectImage;
        this.objectName = objectName;
        this.objectDate = objectDate;
        this.objectPeriod = objectPeriod;
        this.objectCulture = objectCulture;
        this.previousButton = previousButton;
        this.nextButton = nextButton;
        this.departmentComboBox = departmentComboBox;
    }

    public void requestDepartments() {
        service.getDepartments().enqueue(new Callback<MetFeed.DepartmentList>() {
            @Override
            public void onResponse(Call<MetFeed.DepartmentList> call, Response<MetFeed.DepartmentList> response) {
                MetFeed.DepartmentList departmentList = response.body();
                List<MetFeed.DepartmentList.Department> departments = departmentList.departments;
                for (MetFeed.DepartmentList.Department department : departments) {
                    departmentComboBox.addItem(department);
                }

            }

            @Override
            public void onFailure(Call<MetFeed.DepartmentList> call, Throwable t) {
                t.printStackTrace();
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
                requestObjectData(0);
            }

            @Override
            public void onFailure(Call<MetFeed.DepartmentObjects> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void requestObjectData(int objIndex) {
        nextButton.setEnabled(true);
        previousButton.setEnabled(true);
        if (objIndex == objectIDs.size() - 1) {
            nextButton.setEnabled(false);
        }

        if (objIndex == 0) {
            previousButton.setEnabled(false);
        }
        service.getObjectMetadata(objectIDs.get(objIndex)).enqueue(new Callback<MetFeed.Object>() {
            @Override
            public void onResponse(Call<MetFeed.Object> call, Response<MetFeed.Object> response) {

                MetFeed.Object object = response.body();
                assert object != null;
                objectImage.setSize(250, 250);
                if (object.primaryImage.equals("") ) {
                    objectImage.setIcon(null);
                    objectImage.setText("No image to display");
                }
                else {
                    try {
                        URL url = new URL(object.primaryImage);
                        BufferedImage image = ImageIO.read(url);
                        Image scaledImg = image.getScaledInstance(objectImage.getWidth(), objectImage.getHeight(),
                                Image.SCALE_SMOOTH);
                        objectImage.setIcon(new ImageIcon(scaledImg));
                        objectImage.setText("");
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                }

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
