package nl.games.xrebirth.generated;

import nl.games.xrebirth.generated.components.OffsetType;
import nl.games.xrebirth.generated.components.PointType;
import org.testng.annotations.Test;

/**
 * User: bram
 * Date: 14-1-14
 * Time: 18:53
 */
public class VisitorTest {

    @Test
    public void testVisitor() {
        OffsetType o = new OffsetType();

        ToStringVisitor visitor = new ToStringVisitor();
        PointType pointType = new PointType();
        pointType.setX(0.1);
        pointType.accept(visitor, "root");
        o.setPosition(pointType);

        System.err.println(o.toString());
        System.err.println(visitor.toString());
    }

}
