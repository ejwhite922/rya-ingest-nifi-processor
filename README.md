# Apache NiFi Processor for Ingesting Triples into Rya

[![Build Status](https://travis-ci.org/mtnfog/rya-ingest-nifi-processor.svg?branch=master)](https://travis-ci.org/mtnfog/rya-ingest-nifi-processor)

This project is a NiFi processor that allows for ingesting triples into Rya from a NiFi workflow.

## Building

The project can be built as:

`mvn clean install`

The unit tests require Rya to be running. The tests can be skipped by adding `-DskipTests=true` to the Maven build.

## Using in NiFi

To use this processor, clone the project, build it, and copy the resulting `nar` file to NiFi's `lib` directory and restart NiFi.

![Image of NiFi Flow](https://github.com/mtnfog/rya-ingest-nifi-processor/blob/master/images/nifi-flow.png)

## License

This project is licensed under the Apache Software License, version 2.0.
