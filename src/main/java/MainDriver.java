import source.DirectoryExplorer;
import source.FileHandler;
import translate.ClassDiagramConfig;
import translate.UmlTranslator;
import visitors.ClassVisitor;
import visitors.EnumVisitor;
import visitors.InterfaceVisitor;
import visitors.RecordVisitor;

import java.io.File;
import java.io.FileOutputStream;

public class MainDriver {


    public static void main(String[] args) {

        String sourcePath = "src/main/java/";
        if (args.length < 1) {
            System.out.println("Need to pass path of source as argument");
            System.exit(1);
        }

        for (int i = 0; i < args.length; ) {

            System.out.println(args[i]);
            sourcePath = args[i];
            i++;

        }

        try {

            UmlTranslator umlTranslator=new UmlTranslator();
            ClassDiagramConfig config= new ClassDiagramConfig.Builder()
                    .withVisitor(new ClassVisitor(umlTranslator))
                    .withVisitor(new InterfaceVisitor(umlTranslator))
                    .withVisitor(new EnumVisitor(umlTranslator))
                    .withVisitor(new RecordVisitor(umlTranslator))
                    .setShowMethods(true)
                    .setShowAttributes(true)
                    .setShowColoredAccessSpecifiers(false)
                    .build();
            UmlTranslator.config = config;

            FileHandler handler = new FileHandler(umlTranslator);

            File resourceDir = new File(sourcePath);
            if (resourceDir.exists()) {
                new DirectoryExplorer(handler).explore(resourceDir);
            } else {
                System.out.println("File/Folder doesn't exist!");
                System.exit(1);
            }

            File f = new File("output.puml");
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(umlTranslator.toPlantUml().getBytes());
            fos.close();
            System.out.println("PlantUml syntax generated in output file.");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
