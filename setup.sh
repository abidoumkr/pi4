#!/bin/bash

set -e

# Config NFS
echo "/data/nfs/rpi-root *(rw,sync,no_subtree_check,no_root_squash)" > /etc/exports
echo "/data/nfs/rpi-boot *(ro,sync,no_subtree_check,no_root_squash)" >> /etc/exports

# Config DNSMASQ
cat > /etc/dnsmasq.conf <<EOF
port=0
log-dhcp
interface=eth0
bind-interfaces
dhcp-range=192.168.1.100,192.168.1.200,12h
enable-tftp
tftp-root=/data/nfs/rpi-boot
pxe-service=0,"Raspberry Pi Boot"
EOF

# Assure les dossiers existent
mkdir -p /data/nfs/rpi-root
mkdir -p /data/nfs/rpi-boot

# Démarrer services
exportfs -ra
service nfs-kernel-server restart
service dnsmasq restart
service tftpd-hpa restart

# Rester actif
tail -f /dev/null