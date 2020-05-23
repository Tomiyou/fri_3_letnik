make N="${2:-20}" "$1" && mmixal "$1.mmix" && mmix -i "$1.mmix.mmo"
