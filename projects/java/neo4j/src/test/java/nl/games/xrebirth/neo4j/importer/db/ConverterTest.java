package nl.games.xrebirth.neo4j.importer.db;

import jodd.bean.BeanVisitor;
import jodd.typeconverter.TypeConverterManagerBean;
import jodd.typeconverter.impl.DoubleArrayConverter;
import jodd.util.NameValue;
import nl.games.xrebirth.generated.AbstractElement;
import nl.games.xrebirth.generated.components.OffsetType;
import nl.games.xrebirth.generated.components.PointType;
import nl.games.xrebirth.generated.components.SoundType;
import nl.games.xrebirth.generated.components.QuaternionType;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * User: bram
 * Date: 14-1-14
 * Time: 15:37
 */
public class ConverterTest {

    @Test
    public void testConvert() {
        Map<String, Object> result = new HashMap<>();
        PointType p = new PointType();
        p.setX(1.0);
        p.setY(2.0);
        p.setZ(3.0);


        SoundType soundType = new SoundType();
        soundType.setRef("jo");
        OffsetType o = new OffsetType();
        o.setQName(new QName("http://", "jo"));
        soundType.setOffset(o);
        o.setPosition(p);
        p.setParentElement(o);
        QuaternionType  qt = new QuaternionType();
        qt.setQw(1.0);
        qt.setQy(4.0);
        o.setQuaternion(qt);
        BeanVisitor bv = new HashMapBeanVisitor(soundType, result);
        bv.visit();
        System.err.println(result);

    }

    public static class HashMapBeanVisitor extends BeanVisitor {

        TypeConverterManagerBean typeConverterManager = new TypeConverterManagerBean();

        Map<String, Object> result;
        private Stack<NameValue<String, Object>> stack = new Stack<>();
        private Stack<Object> visited = new Stack<>();

        public HashMapBeanVisitor(Object source, Map<String, Object> result) {
            typeConverterManager.register(double[].class, new PointTypeConverter(typeConverterManager));
            this.result = result;
            stack.push(new NameValue<String, Object>("", source));
        }

        public Map<String, Object> result() {
            return result;
        }

        String prefix;

        @Override
        public void visit() {
            while (!stack.empty()) {
                NameValue<String, Object> nv = stack.pop();
                this.source = nv.getValue();
                this.prefix = nv.getName();
                visited.push(this.source);
                super.visit();
            }
        }

        @Override
        protected boolean visitProperty(String name, Object value) {
            if (value == null) {
                return true;
            }
            if (value instanceof AbstractElement) {
                if (visited.contains(value)) {
                    return true;
                } else  if (value instanceof PointType) {
                    result.put(prefix  + name, typeConverterManager.convertType(value, double[].class));
                } else {
                    stack.push(new NameValue<String, Object>(prefix + name + ".", value));
                }
            } else {
                result.put(prefix + name, value);
            }
            return true;
        }
    }

    public static class PointTypeConverter extends DoubleArrayConverter {

        public PointTypeConverter(TypeConverterManagerBean typeConverterManagerBean) {
            super(typeConverterManagerBean);
        }

        @Override
        protected double[] convertValueToArray(Object value) {
            if (value instanceof PointType) {
                PointType pt = (PointType) value;
                double[] array = new double[3];
                array[0] = pt.getX().doubleValue();
                array[1] = pt.getY().doubleValue();
                array[2] = pt.getZ().doubleValue();
                return array;
            } else {
                return super.convertValueToArray(value);
            }
        }
    }
}
