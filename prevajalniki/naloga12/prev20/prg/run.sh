make N="${2:-20}" "$1" && mmixal "$1.mms" && mmix -i "$1.mmo"
