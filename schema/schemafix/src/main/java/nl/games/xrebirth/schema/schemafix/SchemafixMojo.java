package nl.games.xrebirth.schema.schemafix;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.Properties;

/**
 * Goal which touches a timestamp file.
 */
@Mojo(name = "fix", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class SchemafixMojo extends AbstractMojo {

    /**
     * Location of the file.
     */
    @Parameter(defaultValue = "${project.build.sourceDirectory}/../xsd", property = "inputDir", required = true)
    private File inputDirectory;

    /**
     * Location of the file.
     */
    @Parameter(defaultValue = "${project.build.directory}/xsd", property = "outputDir", required = true)
    private File outputDirectory;

    @Parameter(property = "namespaceMapping", required = false)
    private Properties namespaceMapping;

    private SchemaFix fixer;

    public void execute() throws MojoExecutionException {
        fixer = new SchemaFixInludes(this.namespaceMapping);
        File outputDir = outputDirectory;
        File inputDir = inputDirectory;
        if (!inputDir.exists()) {
            getLog().info("directory not found:" + inputDir.getAbsolutePath());
            return;
        }

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        File[] files = inputDir.listFiles();
        if (files == null) {
            getLog().info("directory not found:" + inputDir.getAbsolutePath());
            return;

        }
        for (File in : files) {
            getLog().info("fixing:" + in.getName());
            fixer.execute(in, new File(outputDir, in.getName()));
        }
    }
}
