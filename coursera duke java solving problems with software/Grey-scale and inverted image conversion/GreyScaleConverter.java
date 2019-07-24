import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;


public class GreyScaleConverter {

    public ImageResource getGrey(ImageResource image){
        ImageResource outImage=new ImageResource(image.getWidth(),image.getHeight());
        for(Pixel p:outImage.pixels()){
            Pixel colorPixel= image.getPixel(p.getX(),p.getY());
            int greyScalevalue=(colorPixel.getBlue()+colorPixel.getRed()+colorPixel.getGreen())/3;
            p.setBlue(greyScalevalue);
            p.setGreen(greyScalevalue);
            p.setRed(greyScalevalue);
        }
        return outImage;
    }

    public void greyTester(){
        ImageResource ir= new ImageResource();
        ImageResource res=getGrey(ir);
        res.draw();
    }

    public void selectAndSaveGrey(){
        DirectoryResource dir= new DirectoryResource();
        for(File f :dir.selectedFiles()){
            ImageResource image= new ImageResource(f);
            ImageResource greyImage=getGrey(image);
            String nameGrey="grey-"+f.getName();
            greyImage.setFileName(nameGrey);
            greyImage.save();
        }

    }

    public static void main(String args[]){
        GreyScaleConverter gsc= new GreyScaleConverter();
        //gsc.greyTester();
        gsc.selectAndSaveGrey();
    }
}
