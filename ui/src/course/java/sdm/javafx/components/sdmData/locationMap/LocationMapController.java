package course.java.sdm.javafx.components.sdmData.locationMap;

import course.java.sdm.engine.dto.CustomerDto;
import course.java.sdm.engine.dto.StoreDto;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class LocationMapController extends LocationMapData {

    @FXML private ScrollPane scrollPane;

    private int minX = maxLocation;
    private int maxX = minLocation;
    private int minY = maxLocation;
    private int maxY = minLocation;

    private GridPane gridPane;

    private static final int LOCATION_MAP_WIDTH = 40;
    private static final int LOCATION_MAP_HEIGHT = 40;
    private static final String LOCATION_MAP_CORNER_TITLE = "X / Y";


    public void createLocationMap() {
        calcMinAndMaxLocations();
        minAndMaxPadding();
        gridPane = new GridPane();
        createLocationMapGridPaneTitles();
        gridPane.setGridLinesVisible(true);






        scrollPane.setContent(gridPane);
    }

    private void createLocationMapGridPaneTitles() {
        int numberOfRows = maxY - minY + 1;
        int numberOfColumns = maxX - minX + 1;
        for (int i = 0; i < numberOfColumns; i++) {
            ColumnConstraints column = new ColumnConstraints(LOCATION_MAP_WIDTH);
            gridPane.getColumnConstraints().add(column);
        }
        for (int i = 0; i < numberOfRows; i++) {
            RowConstraints row = new RowConstraints(LOCATION_MAP_HEIGHT);
            gridPane.getRowConstraints().add(row);
        }
        Label cornerTitleLabel = new Label(LOCATION_MAP_CORNER_TITLE);
        gridPane.add(cornerTitleLabel, 0, 0);
        GridPane.setHalignment(cornerTitleLabel, HPos.CENTER);
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (i == 0 && j != 0) {
                    addTitleToLocationMapGridPane(minY + j, j, 0);
                }
                if (j == 0 && i != 0) {
                    addTitleToLocationMapGridPane(minX + i, 0, i);
                }
            }
        }
    }

    private void addTitleToLocationMapGridPane(int titleNumber, int column, int row) {
        String str = String.format("%d", titleNumber);
        Label titleLabel = new Label(str);
        gridPane.add(titleLabel, column, row);
        GridPane.setHalignment(titleLabel, HPos.CENTER);
    }

    private void calcMinAndMaxLocations() {
        int storeXLocation, storeYLocation;
        int customerXLocation, customerYLocation;

        for (StoreDto store : stores) {
            storeXLocation = store.getXLocation();
            storeYLocation = store.getYLocation();

            setMinXAndMaxX(storeXLocation);
            setMinYAndMaxY(storeYLocation);
        }

        for (CustomerDto customer : customers) {
            customerXLocation = customer.getXLocation();
            customerYLocation = customer.getYLocation();

            setMinXAndMaxX(customerXLocation);
            setMinYAndMaxY(customerYLocation);
        }
    }

    private void setMinXAndMaxX(int x) {
        if (x < minX) {
            minX = x;
        }
        if (x > maxX) {
            maxX = x;
        }
    }

    private void setMinYAndMaxY(int y) {
        if (y < minY) {
            minY = y;
        }
        if (y > maxY) {
            maxY = y;
        }
    }

    private void minAndMaxPadding() {
        if (minX > minLocation) {
            minX -= 1;
        }
        if (minY > minLocation) {
            minY -= 1;
        }

        if (maxX < maxLocation) {
            maxX += 1;
        }
        if (maxY < maxLocation) {
            maxY += 1;
        }
    }
}
