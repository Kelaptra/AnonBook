import jakarta.servlet.http.Part;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;

public class ImagesManager {
    private static ImagesManager imagesManager;
//    private static HashMap<String, InputStream> imageInputStreamsByFileName;

    private ImagesManager(){
//        imageInputStreamsByFileName = new HashMap<>();
    }
    public static ImagesManager getImagesManager() {
        if(imagesManager == null){
            imagesManager = new ImagesManager();
        }
        return imagesManager;
    }

    public String getImageURL(Part part) throws IOException {
        byte[] buffer = new byte[1024 * 1024 * 5];
        part.getInputStream().read(buffer);
        String base64Image = Base64.getEncoder().encodeToString(buffer);
        String dataURL = "data:" + part.getContentType() + ";base64," + base64Image;
        return dataURL;
    }


}
