## Axon

This is a POC about CQRS and Event Sourcing using Axon framework 

## Scripts

Two scripts are included to easily start middleware using Docker matching the properties files:

- `start_axon_server.sh` to start an Axon Server instance

## Relevant Points

- Event Store and Token Store need to be configured properly
  - https://docs.axoniq.io/reference-guide/v/4.0/configuring-infrastructure-components/event-processing/event-bus-and-event-store
  - https://docs.axoniq.io/reference-guide/v/4.0/configuring-infrastructure-components/event-processing/event-processors


### Relevant articles

- [A Guide to the Axon Framework](https://www.baeldung.com/axon-cqrs-event-sourcing)
- [Snapshotting Aggregates in Axon](https://www.baeldung.com/axon-snapshotting-aggregates)
- [Dispatching Queries in Axon Framework](https://www.baeldung.com/axon-query-dispatching)
- [Persisting the Query Model](https://www.baeldung.com/axon-persisting-query-model)