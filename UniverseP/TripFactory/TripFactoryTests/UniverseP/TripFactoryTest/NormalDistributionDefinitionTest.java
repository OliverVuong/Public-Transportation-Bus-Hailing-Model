package UniverseP.TripFactoryTest;

import UniverseP.TripFactory.NormalDistributionDefinition;
import UniverseP.TripFactory.NormalLocation;
import org.junit.Test;

/**
 * Created by EG OLIVER RC on 1/25/2018.
 */
public class NormalDistributionDefinitionTest {

    /*
    Error:(22, 20) java: cannot access org.hamcrest.Matcher class file for org.hamcrest.Matcher not found

    @Rule
    public final ExpectedException myException = ExpectedException.none();

    @Test
    public void createNormalDistDef() {
        NormalLocation spawn = new NormalLocation(-47,55,13);
        NormalLocation destination = new NormalLocation(10, 60, 4);

        myException.expect(IllegalArgumentException.class);
        NormalDistributionDefinition.createNormalDistDef(spawn, destination, 50, 10);
    }
    */

    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionSpawn() {
        NormalLocation spawn = new NormalLocation(-47,55,13);
        NormalLocation destination = new NormalLocation(10, 60, 4);
        NormalDistributionDefinition.createNormalDistDef(spawn, destination, 50, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionDestination() {
        NormalLocation spawn = new NormalLocation(47,55,13);
        NormalLocation destination = new NormalLocation(10, -60, 4);
        NormalDistributionDefinition.createNormalDistDef(spawn, destination, 50, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTime() {
        NormalLocation spawn = new NormalLocation(47,55,13);
        NormalLocation destination = new NormalLocation(10, 60, 4);
        NormalDistributionDefinition.createNormalDistDef(spawn, destination, -50, 10);
    }
}