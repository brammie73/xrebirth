package nl.games.xrebirth.jaxb.visitor;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JConditional;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.BadCommandLineException;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.CClassInfoParent;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.outline.Aspect;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.outline.PackageOutline;
import com.sun.xml.bind.v2.model.core.AttributePropertyInfo;
import com.sun.xml.bind.v2.model.core.ElementPropertyInfo;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Iterator;

/**
 * Hello world!
 */
public class VisitorPlugin extends com.sun.tools.xjc.Plugin {

    public static final String NAME = "Xpv";
    public static final String PACKAGE_NAME = "Xpvpackage";

    private String packageName;

    @Override
    public String getOptionName() {
        return NAME;
    }

    @Override
    public String getUsage() {
        return NAME;
    }

    public String getPackageName() {
        return packageName;
    }

    @Override
    public int parseArgument(Options opt, String[] args, int index) throws BadCommandLineException, IOException {
        String arg = args[index];
        if (arg.startsWith("-" + PACKAGE_NAME + ":")) {
            this.packageName = arg.split(":")[1];
            return 1;
        }
        return 0;
    }

    @Override
    public boolean run(Outline outline, Options opt, ErrorHandler errorHandler) throws SAXException {
        if  (packageName == null || packageName.isEmpty()) {
            for (PackageOutline packageOutline : outline.getAllPackageContexts()) {
                Holder h = createInterfaces(outline, packageOutline._package());
                createImplementations(packageOutline, h);
            }
        } else {
            JPackage interfacePackage = getCreatePackage(outline);
            Holder h = createInterfaces(outline, interfacePackage);
            for (PackageOutline packageOutline : outline.getAllPackageContexts()) {
                createImplementations(packageOutline, h);
            }
        }
        return true;
    }

    private JPackage getCreatePackage(Outline outline) {
        JPackage vizPackage = null;
        if (getPackageName() != null) {
            JPackage root = outline.getCodeModel().rootPackage();
            String[] packages = getPackageName().split("\\.");
            JPackage current = root;
            for(String p : packages) {
                current = current.subPackage(p);
            }
            vizPackage = current;
        }
        if (vizPackage == null) {
            PackageOutline packageOutline = outline.getAllPackageContexts().iterator().next();
            CClassInfoParent.Package pkage = new CClassInfoParent.Package(packageOutline._package());
            vizPackage = (JPackage) outline.getContainer(pkage, Aspect.IMPLEMENTATION);
        }
        return vizPackage;
    }

    private static class Holder {
        private Holder(JDefinedClass iVisitor, JDefinedClass iVisitable, JMethod objectVisit, JMethod accept) {
            this.iVisitor = iVisitor;
            this.iVisitable = iVisitable;
            this.objectVisit = objectVisit;
            this.accept = accept;
        }
        JDefinedClass iVisitor;
        JDefinedClass iVisitable;
        JMethod objectVisit;
        JMethod objectVisitEnd;
        JMethod accept;
    }


    protected Holder createInterfaces(Outline outline, JPackage jpackage) {
        JDefinedClass iVisitor = outline.getClassFactory().createInterface(jpackage, "PropertyVisitor", null);
        JDefinedClass iVisitable = outline.getClassFactory().createInterface(jpackage, "PropertyVisitable", null);
        JMethod propertyVisit = iVisitor.method(JMod.PUBLIC, void.class, "visit");
        propertyVisit.param(iVisitable, "aVisitable");
        propertyVisit.param(String.class, "name");
        propertyVisit.param(Object.class, "value");
        JMethod objectVisit = iVisitor.method(JMod.PUBLIC, boolean.class, "visitStart");
        objectVisit.param(iVisitable, "aVisitable");
        objectVisit.param(String.class, "name");
        JMethod objectVisitEnd = iVisitor.method(JMod.PUBLIC, void.class, "visitEnd");
        objectVisitEnd.param(iVisitable, "aVisitable");

        JMethod accept = iVisitable.method(JMod.PUBLIC, void.class, "accept");
        accept.param(iVisitor, "aVisitor");
        accept.param(String.class, "name");
        Holder h =  new Holder(iVisitor, iVisitable, objectVisit, accept);
        h.objectVisitEnd = objectVisitEnd;
        return h;
    }

    private void createImplementations(PackageOutline packageOutline, Holder holder) {
        JDefinedClass iVisitor = holder.iVisitor;
        JDefinedClass iVisitable = holder.iVisitable;
        JMethod objectVisit = holder.objectVisit;
        JMethod objectVisitEnd = holder.objectVisitEnd;
        JMethod accept = holder.accept;

        //implements interface
        for (ClassOutline classOutline : packageOutline.getClasses()) {
            JDefinedClass definedClass = classOutline.implClass;
            definedClass._implements(iVisitable);
        }

        //add implementation
        for (ClassOutline classOutline : packageOutline.getClasses()) {
            JMethod method = classOutline.implClass.method(JMod.PUBLIC, void.class, "accept");
            JVar aVisitor = method.param(iVisitor, "aVisitor");
            JVar aName = method.param(String.class, "aName");
            JBlock block = method.body();

            //visit start
            block  =  block._if(JExpr.invoke(aVisitor, objectVisit).arg(JExpr._this()).arg(aName))._then();

            //invoke super
            if (isClass(classOutline.getSuperClass(), iVisitable)) {
                block.invoke(JExpr._super(), accept).arg(aVisitor).arg(aName);
            }
            JDefinedClass classImpl = classOutline.implClass;
            //loop fields
            for (FieldOutline fieldOutline : classOutline.getDeclaredFields()) {
                JFieldVar fieldVar = classImpl.fields().get(fieldOutline.getPropertyInfo().getName(false));
                if (fieldVar.type().isPrimitive()) {
                    block.invoke(aVisitor, "visit").arg(JExpr._this()).arg(name(fieldOutline, true)).arg(JExpr.invoke(getter(fieldOutline)));
                } else {
                    JClass fieldClass = (JClass) fieldVar.type();
                    if (fieldClass.isParameterized()) {
                        //todo:List<Serializabe>
                        JClass  param =  fieldClass.getTypeParameters().get(0);
                        if (isClass(param, iVisitable)) {
                            //List<Visitable>
                            block.forEach(iVisitable, "entry", JExpr.invoke(getter(fieldOutline)))
                                    .body()
                                    .invoke(JExpr.ref("entry"), accept).arg(aVisitor).arg(name(fieldOutline, true));
                        } else if(!param.isAssignableFrom(iVisitable)) {
                            //List<String>
                            block.forEach(param, "entry", JExpr.invoke(getter(fieldOutline)))
                                    .body()
                                    .invoke(aVisitor, "visit").arg(JExpr._this()).arg(name(fieldOutline, true)).arg(JExpr.ref("entry"));
                        } else {
                            //List<Object> mixed
                            JConditional conditional = block.forEach(param, "entry", JExpr.invoke(getter(fieldOutline)))
                                    .body()
                                    ._if(JExpr.ref("entry")._instanceof(iVisitable));
                            conditional._then()
                                    .invoke(JExpr.cast(iVisitable, JExpr.ref("entry")), accept).arg(aVisitor).arg(name(fieldOutline, true));
                            JBlock elseBlock= conditional._else();
                            elseBlock.invoke(aVisitor, "visit").arg(JExpr._this()).arg(name(fieldOutline, true)).arg(JExpr.invoke(getter(fieldOutline)));
                        }
                    } else if (isClass(fieldClass, iVisitable)) {
                        //if  visitable != null then accept(visit)  else start/end
                        JConditional conditional = block._if(JExpr._null().ne(JExpr.invoke(getter(fieldOutline))));
                        conditional._then()
                                .invoke(JExpr.invoke(getter(fieldOutline)), accept).arg(aVisitor).arg(name(fieldOutline, true));
                        JBlock elseBlock = conditional._else();
                        elseBlock.invoke(aVisitor, objectVisit).arg(JExpr._null()).arg(name(fieldOutline, true));
                        elseBlock.invoke(aVisitor, objectVisitEnd).arg(JExpr._null());
                    } else {
                        block.invoke(aVisitor, "visit").arg(JExpr._this()).arg(name(fieldOutline, true)).arg(JExpr.invoke(getter(fieldOutline)));
                    }
                }
            }
            //visit end
            block.invoke(aVisitor, objectVisitEnd).arg(JExpr._this());

        }
    }


    private static boolean isClass(ClassOutline classOutline, JDefinedClass definedClass) {
        if (classOutline != null) {
            return isClass(classOutline.implClass, definedClass);
        }
        return false;
    }

    private static boolean isClass(JClass aClass, JDefinedClass definedClass) {
        Iterator<JClass> it = aClass._implements();
        while (it.hasNext()) {
            JClass jClass = it.next();
            if (jClass.isAssignableFrom(definedClass)) {
                return true;
            }
        }
        return false;
    }

    private static String name(FieldOutline fieldOutline, boolean... xml) {
        CPropertyInfo pi = fieldOutline.getPropertyInfo();
        if (xml.length > 0 && xml[0]) {
            switch (pi.kind()) {
                case ATTRIBUTE:
                    AttributePropertyInfo api = (AttributePropertyInfo) pi;
                    return api.getXmlName().getLocalPart();
                case ELEMENT:
                    ElementPropertyInfo epi = (ElementPropertyInfo) pi;
                    return (epi.getXmlName() == null) ? epi.getName() : epi.getXmlName().getLocalPart();
                default:
                    return pi.getName(false);
            }
        }
        return pi.getName(false);
    }

    private static JMethod getter(FieldOutline fieldOutline) {
        final JDefinedClass theClass = fieldOutline.parent().implClass;
        final String publicName = fieldOutline.getPropertyInfo().getName(true);
        final JMethod getgetter = theClass.getMethod("get" + publicName, NONE);
        if (getgetter != null) {
            return getgetter;
        } else {
            final JMethod isgetter = theClass.getMethod("is" + publicName, NONE);
            if (isgetter != null) {
                return isgetter;
            } else {
                return null;
            }
        }
    }

    private static final JType[] NONE = new JType[0];
}
