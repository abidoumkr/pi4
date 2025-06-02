FROM debian:bookworm

RUN apt-get update && apt-get install -y \
    nfs-kernel-server \
    dnsmasq \
    tftpd-hpa \
    iproute2 \
    iputils-ping \
    wget \
 && apt-get clean && rm -rf /var/lib/apt/lists/*

COPY setup.sh /setup.sh
RUN chmod +x /setup.sh

CMD ["/setup.sh"]