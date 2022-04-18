#!/bin/bash
MINIO_ROOT_USER=minio MINIO_ROOT_PASSWORD=minio123 ./minio server /root/minio-data --console-address ":9001"

