# Cloud balancing benchmark for solver factory

  This is a sample project created from the simplified cloud 
balancing solution. The main idea is to compare average time 
spent on building solution that use DRL or Constraint Streams.

Before running the benchmark make sure you build it,
it is better to avoid side numbers of measuring work of cycles 
that creates the load itself. Build the project with:
 
`mvn clean install -DskipTests`

Then execute the benchmark

`java -jar target/cloud-balancing-solver-benchmarks.jar`

- **buildSolverFactoryCs** benchmark of solution build on factory that used configuration for Constraint Stream provider class
- **buildSolverFactoryDrl** benchmark of solution build on factory that used configuration for Drl 