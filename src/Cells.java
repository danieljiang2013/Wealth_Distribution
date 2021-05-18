import java.util.ArrayList;
import java.util.List;

class Cells {

    private int size;
    private List<List<Cell>> patches;
    private final static double diffuseNumber = 0.25;

    public Cells(int size) {
        this.size = size;
        this.patches = new ArrayList<List<Cell>>();
        for (int x = 0; x < size; x++) {
            var column = new ArrayList<Cell>();
            for (int y = 0; y < size; y++) {
                column.add(new Cell(x, y));
            }
            patches.add(column);
        }
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                setupBestLand(patches.get(x).get(y));
            }
        }

        for (int x = 0; x < 5; x++) {
            repeat5task();
        }
        /*
        for (int x = 0; x < 10; x++) {
            repeat10task();
        }
        maximiseInitialGrain();

         */
    }

    public void printCells() {
        System.out.println("Position | Grain | Max Grain ");
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                var patch = patches.get(x).get(y);
                System.out.printf("%s,%s     | %d | %d \n", patch.getX(), patch.getY(), patch.getGrain(), patch.getMaxGrain());
            }
        }
    }

    private double randomize() {
        return (Math.random());
    }

    public Cell getCellAt(int x, int y) {
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
    private void setupBestLand(Cell patch) {
        patch.setMaxGrain(0);
        if (Configuration.percentBestLand >= randomize()) {
            patch.setMaxGrain(Configuration.maxGrain);
            patch.setInitialGrain(Configuration.maxGrain);
        }
    }

    // This method returns a list of Cells that are the neighbours of the
    // provided cell.
    private List<Cell> getNeighbours(Cell patch) {
        var location = patch.getPosition();
        var neighbours = new ArrayList<Cell>();

        try {
            neighbours.add(patches.get(location.x - 1).get(location.y)); // one above
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("No neighbour above");
        }
        try {
            neighbours.add(patches.get(location.x + 1).get(location.y)); // one below
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("No neighbour below");
        }
        try {
            neighbours.add(patches.get(location.x).get(location.y - 1)); // one to left
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("No neighbour to the left");
        }
        try {
            neighbours.add(patches.get(location.x).get(location.y + 1)); // one to right
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("No neighbour to the right");
        }
        try {
            neighbours.add(patches.get(location.x - 1).get(location.y - 1)); // top left
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("No neighbour top left");
        }
        try {
            neighbours.add(patches.get(location.x - 1).get(location.y + 1)); // top right
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("No neighbour top right");
        }
        try {
            neighbours.add(patches.get(location.x + 1).get(location.y - 1)); // bottom left
        } catch (IndexOutOfBoundsException e) {
           // System.out.println("No neighbour bottom left");
        }
        try {
            neighbours.add(patches.get(location.x + 1).get(location.y + 1)); // bottom right
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("No neighbour bottom right");
        } finally {
            if (neighbours.size() != 0 && neighbours.size() <= 8) {
                return neighbours;
            }
        }
        return null;
    }

    private void diffuseGrain(Cell patch, List<Cell> neighbours) {
        final int originalGrainValue = patch.getGrain();
        final double grainToDiffuse = (originalGrainValue * (this.diffuseNumber * 100));
        final double equalSizeGrain = grainToDiffuse / 8;
        int grainDistributed = 0;

        // Each neighbor gets an eighth share;
        for (int i = 0; i < neighbours.size(); i++) {
            var neighbour = neighbours.get(i);
            var newGrain = neighbour.getGrain() + equalSizeGrain;
            neighbour.setInitialGrain((int) newGrain);
            grainDistributed += equalSizeGrain;
        }

        // the patch keeps any leftover shares
        patch.setInitialGrain(originalGrainValue - grainDistributed);
    }

    private void repeat5task() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Cell patch = patches.get(x).get(y);
                if (patch.getMaxGrain() != 0) {
                    patch.setInitialGrain(patch.getMaxGrain());
                }
                // need to verify if this is placed correctly w.r.t Netlogo if/else
                diffuseGrain(patch, getNeighbours(patch));
            }
        }
    }

    private void repeat10task() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Cell patch = patches.get(x).get(y);
                diffuseGrain(patch, getNeighbours(patch));
            }
        }
    }
    private void maximiseInitialGrain(){
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                var newMaxGrain = patches.get(x).get(y).getGrain();
                patches.get(x).get(y).setMaxGrain(newMaxGrain);
            }
        }
    }
}