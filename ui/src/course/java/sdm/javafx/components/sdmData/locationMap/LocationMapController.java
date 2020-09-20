package course.java.sdm.javafx.components.sdmData.locationMap;

import course.java.sdm.engine.dto.CustomerDto;
import course.java.sdm.engine.dto.StoreDto;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class LocationMapController extends LocationMapData {

    @FXML private ScrollPane scrollPane;

    private int minX = maxLocation;
    private int maxX = minLocation;
    private int minY = maxLocation;
    private int maxY = minLocation;

    int numberOfRows;
    int numberOfColumns;

    private GridPane gridPane;

    private static final String LOCATION_MAP_CORNER_TITLE = "Y / X";
    private static final int LOCATION_MAP_CELL_WIDTH = 40;
    private static final int LOCATION_MAP_CELL_HEIGHT = 40;
    private static final int STORE_IMG_WIDTH = 35;
    private static final int STORE_IMG_HEIGHT = 35;
    private static final int CUSTOMER_IMG_WIDTH = 30;
    private static final int CUSTOMER_IMG_HEIGHT = 30;
    private static final String STORE_IMG_URL_STR = "course/java/sdm/javafx/components/sdmData/locationMap/images/store-orange-icon.png";
    private static final String CUSTOMER_IMG_URL_STR = "course/java/sdm/javafx/components/sdmData/locationMap/images/customer-green-icon.png";


    public void createLocationMap() {
        calcMinAndMaxLocations();
        minAndMaxPadding();
        gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: white;");
        calcNumberOfRowsAndCols();
        createLocationMapGridPaneTitles();
        gridPane.setGridLinesVisible(true);
        setStoresInLocationMap();
        setCustomersInLocationMap();
        scrollPane.setContent(gridPane);
    }

    private void setStoresInLocationMap() {
        int storeXLocation, storeYLocation;
        int col, row;
        for (StoreDto store : stores) {
            storeXLocation = store.getXLocation();
            storeYLocation = store.getYLocation();
            row = storeYLocation - minY;
            col = storeXLocation - minX;

            Image image = new Image(STORE_IMG_URL_STR);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(STORE_IMG_HEIGHT);
            imageView.setFitWidth(STORE_IMG_WIDTH);

            imageView.setPreserveRatio(true);
            Label label = new Label();
            label.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            label.setGraphic(imageView);
            label.setTooltip(new Tooltip(getStoreInfoForTooltip(store)));

            gridPane.add(label, col, row);
            GridPane.setHalignment(label, HPos.CENTER);
        }
    }

    private void setCustomersInLocationMap() {
        int customerXLocation, customerYLocation;
        int col, row;
        for (CustomerDto customer : customers) {
            customerXLocation = customer.getXLocation();
            customerYLocation = customer.getYLocation();
            row = customerYLocation - minY;
            col = customerXLocation - minX;

            Image image = new Image(CUSTOMER_IMG_URL_STR);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(CUSTOMER_IMG_HEIGHT);
            imageView.setFitWidth(CUSTOMER_IMG_WIDTH);

            imageView.setPreserveRatio(true);
            Label label = new Label();
            label.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            label.setGraphic(imageView);
            label.setTooltip(new Tooltip(getCustomerInfoForTooltip(customer)));

            gridPane.add(label, col, row);
            GridPane.setHalignment(label, HPos.CENTER);
        }
    }

    private String getStoreInfoForTooltip(StoreDto storeDto) {
        int id = storeDto.getId();
        String name = storeDto.getName();
        int ppk = storeDto.getPpk();
        int xLocation = storeDto.getXLocation();
        int yLocation = storeDto.getYLocation();
        return String.format("(X = %d, Y = %d)\n" +
                "ID: %d\nName: %s\nPPK: %d", xLocation, yLocation, id, name, ppk);
    }

    private String getCustomerInfoForTooltip(CustomerDto customerDto) {
        int id = customerDto.getId();
        String name = customerDto.getName();
        int xLocation = customerDto.getXLocation();
        int yLocation = customerDto.getYLocation();
        return String.format("(X = %d, Y = %d)\n" +
                "ID: %d\nName: %s", xLocation, yLocation, id, name);
    }

    private void calcNumberOfRowsAndCols() {
        numberOfRows = maxY - minY + 1;
        numberOfColumns = maxX - minX + 1;
    }

    private void createLocationMapGridPaneTitles() {
        for (int i = 0; i < numberOfColumns; i++) {
            ColumnConstraints column = new ColumnConstraints(LOCATION_MAP_CELL_WIDTH);
            gridPane.getColumnConstraints().add(column);
        }
        for (int i = 0; i < numberOfRows; i++) {
            RowConstraints row = new RowConstraints(LOCATION_MAP_CELL_HEIGHT);
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
