package org.example.chessgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.util.Objects;

public class PromotionController {

    private ShareData shareData; // this field is for exchanging data between this GUI and the board GUI

    private COLOR color;

    private Box location;

    @FXML
    Button queen,bishop,knight,rook;

    public void  initPromotion(ShareData shareData,COLOR color,Box location){
        this.shareData = shareData;
        this.color = color;
        this.location = location;
        initButtonsIcon();
    }

    private void initButtonsIcon(){
        String imagePath = GuiBoard.getPieceIconPath(ID.Queen, color);
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), 60,60, true, true);
        queen.setGraphic(new ImageView(img));

        imagePath = GuiBoard.getPieceIconPath(ID.Bishop, color);
        img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), 60,60, true, true);
        bishop.setGraphic(new ImageView(img));

        imagePath = GuiBoard.getPieceIconPath(ID.Knight, color);
        img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), 60,60, true, true);
        knight.setGraphic(new ImageView(img));

        imagePath = GuiBoard.getPieceIconPath(ID.Rook, color);
        img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), 60,60, true, true);
        rook.setGraphic(new ImageView(img));

    }

    @FXML
    private void buttonPressed(ActionEvent event){
        String choice = ((Button) event.getSource()).getText();
        switch (choice){
            case "Queen":
                shareData.setId(new Queen(color,location));
                break;
            case "Bishop":
                shareData.setId(new Bishop(color,location));
                break;
            case "Rook":
                shareData.setId(new Rook(color,location));
                break;
            case "Knight":
                shareData.setId(new Knight(color,location));
                break;
        }
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
