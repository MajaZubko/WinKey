package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private Label winKeyLabel;

    @FXML
    private Button copyButton;

    final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();

    public void initialize(){
        String key = "I tried";
        List<String> lines = new ArrayList<>();
        try
        {
            Process p=Runtime.getRuntime().exec("cmd /c wmic path softwarelicensingservice get OA3xOriginalProductKey");
            p.waitFor();
            BufferedReader reader=new BufferedReader(
                    new InputStreamReader(p.getInputStream())
            );
            String line;
            while((line = reader.readLine()) != null)
            {
                lines.add(line);
            }


        }
        catch(IOException e1) {e1.printStackTrace();}
        catch(InterruptedException e2) {e2.printStackTrace();}

        if(!(lines.get(2) == null)){
            key = lines.get(2);
            key = key.replaceAll("\\W", "");
        }
        winKeyLabel.setText(key);
    }


    public void copyButtonAction() {
        content.putString(winKeyLabel.getText());
        clipboard.setContent(content);
    }
}
