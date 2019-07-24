import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class BatchInversion {
    public ImageResource getNegitive(ImageResource image){
        ImageResource outImage=new ImageResource(image.getWidth(),image.getHeight());
        for(Pixel p:outImage.pixels()){
            Pixel colorPixel= image.getPixel(p.getX(),p.getY());
            p.setBlue(255-colorPixel.getBlue());
            p.setGreen(255-colorPixel.getGreen());
            p.setRed(255-colorPixel.getRed());
        }
        return outImage;
    }

    public void selectAndConvert(){
        DirectoryResource dir = new DirectoryResource();
        for(File f:dir.selectedFiles()){
            ImageResource image= new ImageResource(f);
            ImageResource negitive=getNegitive(image);
            String newName="inverted-"+f.getName();
            negitive.setFileName(newName);
            negitive.save();
        }
    }

    public static void main(String args[]){
        BatchInversion bi= new BatchInversion();
        bi.selectAndConvert();
    }
}
