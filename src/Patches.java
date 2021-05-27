import java.util.ArrayList;
import java.util.List;
/**
 * This Class represents a N*N representation of all individual
 * patches present in the grid.This class is responsible for the
 * initial setup & diffusion of grain within the patches.
 */
class Patches {

    // The size of the grid
    private int size;
    // A 2D list of all individual patch
    private List<List<Patch>> patches;
    private final static double diffuseNumber = 0.25;

    /**
     * The constructor set up a 2d grid of single patches
     * and then proceeds to setup best land, diffuse the grain
     * and then finally set the max grain
     * @param size of the grid
     */
    public Patches(int size) {
        this.size = size;
        this.patches = new ArrayList<List<Patch>>();
        // create new patches
        for (int x = 0; x < size; x++) {
            var column = new ArrayList<Patch>();
            for (int y = 0; y < size; y++) {
                column.add(new Patch(x, y));
            }
            patches.add(column);
        }
        // setup the best land
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                setupBestLand(patches.get(x).get(y));
            }
        }
        // diffuse the grain around
        for (int x = 0; x < 5; x++) {
            diffuseGrainSetInitial();
        }
        // diffuse the grain around a bit more
        for (int x = 0; x < 10; x++) {
            diffusePatches();
        }
        // set the max grain
        setMaxGrain();
    }

    public void printCells() {
        System.out.println("x,y | CurrentGrain | Max-Grain ");
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                var patch = patches.get(x).get(y);
                System.out.printf("%s,%s  | %.4f | %.4f \n", patch.getX(), patch.getY(), patch.getGrain(),
                        patch.getMaxGrain());
            }
        }
    }

    private double randomize() {
        return (Math.random());
    }

    public Patch getCellAt(int x, int y) {
        return patches.get(x).get(y);
    }

    public void tick() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                patches.get(x).get(y).tick();
            }
        }
    }

    // Gives some patches the highest amount of grain possible
    // these patches are the "best land"
    private void setupBestLand(Patch patch) {
        patch.setMaxGrain(0);
        if (Configuration.percentBestLand >= randomize()) {
            patch.setMaxGrain(Configuration.maxGrain);
            patch.setInitialGrain(Configuration.maxGrain);
        }
    }

    /**
     * This method returns a list of Patches that are the neighbours of the
     * the patch passed as the param
     * @param patch whose neighbours are to be found
     * @return a list of patches
     */
    private List<Patch> getNeighbours(Patch patch) {
        var location = patch.getPosition();
        var neighbours = new ArrayList<Patch>();

        try {
            neighbours.add(patches.get(location.x - 1).get(location.y));
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("No neighbour above");
        }
        try {
            neighbours.add(patches.get(location.x + 1).get(location.y));
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("No neighbour below");
        }
        try {
            neighbours.add(patches.get(location.x).get(location.y - 1));
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("No neighbour to the left");
        }
        try {
            neighbours.add(patches.get(location.x).get(location.y + 1));
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("No neighbour to the right");
        }
        try {
            neighbours.add(patches.get(location.x - 1).get(location.y - 1));
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("No neighbour top left");
        }
        try {
            neighbours.add(patches.get(location.x - 1).get(location.y + 1));
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("No neighbour top right");
        }
        try {
            neighbours.add(patches.get(location.x + 1).get(location.y - 1));
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("No neighbour bottom left");
        }
        try {
            neighbours.add(patches.get(location.x + 1).get(location.y + 1));
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("No neighbour bottom right");
        } finally {
            if (neighbours.size() != 0 && neighbours.size() <= 8) {
                return neighbours;
            }
        }
        return null;
    }

    /**
     * This method diffuses the grain value from a source patch
     * to its neighbours
     * @param patch from whom grain is to be sourced
     * @param neighbours to whom the grain is to be distributed
     */
    private void diffuseGrain(Patch patch, List<Patch> neighbours) {
        final double originalGrainValue = patch.getGrain();
        final double grainToDiffuse = (originalGrainValue * diffuseNumber);
        final double equalSizeGrain = grainToDiffuse / 8.0f;
        double grainDistributed = 0;

        // Each neighbor gets an eighth share;
        for (int i = 0; i < neighbours.size(); i++) {
            var neighbour = neighbours.get(i);
            var newGrain = neighbour.getGrain() + equalSizeGrain;
            neighbour.setInitialGrain(newGrain);
            grainDistributed += equalSizeGrain;
        }
        // the patch keeps any leftover shares
        patch.setInitialGrain(originalGrainValue - grainDistributed);
    }

    private void diffuseGrainSetInitial() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Patch patch = patches.get(x).get(y);
                if (patch.getMaxGrain() > 30) {
                    patch.setInitialGrain(patch.getMaxGrain());
                }
            }
        }
        diffusePatches();
    }

    /**
     * This method loops over each patch and diffuses its grain
     * to its neighbours
     */
    private void diffusePatches() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Patch patch = patches.get(x).get(y);
                diffuseGrain(patch, getNeighbours(patch));
            }
        }
    }

    /**
     * This method sets the maximum grain value of a patch
     * as the grain value for that patch at the end of setup
     */
    private void setMaxGrain() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                var newMaxGrain = patches.get(x).get(y).getGrain();
                patches.get(x).get(y).setInitialGrain((int) newMaxGrain);
                patches.get(x).get(y).setMaxGrain((int) newMaxGrain);
            }
        }
    }
}