# Wealth Distribution Model - Java Implementation

All configuration and simulation parameters are defined in `src/Configuration.java`. The default value of `simulationType` should be `SimulationType.SingleRun` unless running the extension version

Output is generated in the form of CSV files at this root directory.

## Compile & Run

```shell
javac -d class/ src/*.java 
cd class && java Simulation
```

### Configuration

A simulation can be configured to run in one of 3 ways

Type|Description|`src/Configuration.java`
---|---|---
**SingleRun** (default)|runs the simulation once for `numTicks`|Set `simulationType = SimulationType.SingleRun`
MultipleRuns|runs the simulation `numRuns` times,each for `numTicks`|Set `simulationType = SimulationType.MultipleRuns`
ChangeTaxFrom0To100|runs the simulation 100 times with tax increasing from 1 - 100 after each run. Tax can be selected to be on either 'wealth' or on 'income'| - Set `simulationType = SimulationType.ChangeTaxFrom0To100` & set `taxSystem` as one of `TaxSystem.Wealth` or `TaxSystem.Income`


### Analysis

A folder called analysis has been provided where additional processing was done on the outputted statistics to produce graphs and tables.

