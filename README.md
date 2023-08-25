# POC CQRS WITH AXON

This is a POC about CQRS and Event Sourcing using Axon framework

## Notes
- Axon support Event Store integration only with few DataBases. See the [documentation](https://docs.axoniq.io/reference-guide/axon-framework/events/event-bus-and-event-store) for details 
- Custom implementation are needed for other database integrations
- 
## Dependencies

- Axon Server instance is needed to be used as Event Store. The script `start_axon_server.sh` is included to easily start middleware using Docker


### Relevant articles

- [A Guide to the Axon Framework](https://www.baeldung.com/axon-cqrs-event-sourcing)
- [Snapshotting Aggregates in Axon](https://www.baeldung.com/axon-snapshotting-aggregates)
- [Dispatching Queries in Axon Framework](https://www.baeldung.com/axon-query-dispatching)
- [Persisting the Query Model](https://www.baeldung.com/axon-persisting-query-model)