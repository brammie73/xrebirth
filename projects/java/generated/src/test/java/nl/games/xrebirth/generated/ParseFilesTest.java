package nl.games.xrebirth.generated;

import nl.games.xrebirth.generated.cutscenes.Cutscene;
import nl.games.xrebirth.generated.components.Components;
import nl.games.xrebirth.generated.factions.Factions;
import nl.games.xrebirth.generated.parameters.Parameters;
import nl.games.xrebirth.generated.races.Races;
import nl.games.xrebirth.generated.regiondefinitions.RegionsType;
import nl.games.xrebirth.generated.text.Language;
import nl.games.xrebirth.generated.unlocks.Unlocks;
import nl.games.xrebirth.generated.wares.WaresType;
import org.testng.annotations.Test;

/**
 * Author: bram
 * Date: 5-1-14
 * Time: 2:39
 */
@Test
public class ParseFilesTest {

    public static class ComponentsTest extends AbstractXmlTest<Components> {

        @Override
        public String getFileName() {
            return "libraries/component.xml";
        }
    }

    public static class CutscenesTest extends AbstractXmlTest<Cutscene> {

        @Override
        public String getFileName() {
            return "cutscenes/Ep1C1_Intro_Cutscene1.xml";
        }
    }

    public static class FactionsTest extends AbstractXmlTest<Factions> {

        @Override
        public String getFileName() {
            return "libraries/factions.xml";
        }
    }

    public static class ParametersTest extends AbstractXmlTest<Parameters> {

        @Override
        public String getFileName() {
            return "libraries/parameters.xml";
        }
    }

    public static class RacesTest extends AbstractXmlTest<Races> {

        @Override
        public String getFileName() {
            return "libraries/races.xml";
        }
    }

    public static class RegionsTest extends AbstractXmlTest<RegionsType> {

        @Override
        public String getFileName() {
            return "libraries/region_definitions.xml";
        }
    }

    public static class StatsTest extends AbstractXmlTest<nl.games.xrebirth.generated.stats.Stats> {

        @Override
        public String getFileName() {
            return "libraries/stats.xml";
        }
    }

    public static class TextTest extends AbstractXmlTest<Language> {

        @Override
        public String getFileName() {
            return "t/0001-L044.xml";
        }
    }

    public static class UnlocksTest extends AbstractXmlTest<Unlocks> {

        @Override
        public String getFileName() {
            return "libraries/unlocks.xml";
        }
    }

    public static class WaresTest extends AbstractXmlTest<WaresType> {

        @Override
        public String getFileName() {
            return "libraries/wares.xml";
        }
    }


}
