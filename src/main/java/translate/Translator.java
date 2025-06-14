package translate;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.RecordDeclaration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;

public interface Translator{

    void addClass(ClassOrInterfaceDeclaration c);
    void addEnum(EnumDeclaration c);
    void addInterface(ClassOrInterfaceDeclaration i);
    void addRecord(RecordDeclaration r);
    void addField(FieldDeclaration f);
    void addMethod(MethodDeclaration d);
    void setError(Boolean b);
    void translateFile(File f) throws FileNotFoundException;

}