#!/bin/bash
# 获取ID
cat *.json | grep '"item_id":'|awk '{print $2}' >> id.txt


