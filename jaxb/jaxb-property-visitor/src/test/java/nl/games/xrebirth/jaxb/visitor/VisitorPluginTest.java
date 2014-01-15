package nl.games.xrebirth.jaxb.visitor;

import org.jvnet.jaxb2.maven2.AbstractXJC2Mojo;
import org.jvnet.jaxb2.maven2.test.RunXJC2Mojo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: bram
 * Date: 15-1-14
 * Time: 11:14
 */
public class VisitorPluginTest extends RunXJC2Mojo {

    @Override
    public File getSchemaDirectory() {
        return new File(getBaseDir(), "src/test/resources");
    }

    @Override
    protected void configureMojo(AbstractXJC2Mojo mojo) {
        super.configureMojo(mojo);
        mojo.setForceRegenerate(true);
        //mojo.setBindingExcludes(new String[]{"*.xjb"});
    }

    @Override
    public List<String> getArgs() {
        final List<String> args = new ArrayList<String>(super.getArgs());
        args.add("-Xxew");
        args.add("-" + VisitorPlugin.NAME);
        //args.add("-" + VisitorPlugin.PACKAGE_NAME + ":nl.test");
        return args;
    }

    @Override
    public void testExecute() throws Exception {
        super.testExecute();
    }

}
