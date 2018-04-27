FROM golang:latest
MAINTAINER www.arima.eu

RUN go get github.com/coreos/dex
WORKDIR /go/src/github.com/coreos/dex
RUN make
COPY spring-boot-demo.yaml /go/src/github.com/coreos/dex/examples
RUN cd /go/src/github.com/coreos/dex
CMD ./bin/dex serve examples/spring-boot-demo.yaml

EXPOSE 5556
