# Instructions

## Required materials
* spencer-gen2 board
* meta-spencer

## Prerequisite
* Logged into a shell console
* wifi modules loaded. It is not necessary to have a wifi connection but
  just the wifi mlan0 interface(see qvts/wifi.md)
* check for ttymxc1 serial interface
* Please follow below commands to enable Wifi/BT if not setup
* NOTE: Below prerequisite command needed only if not setup Audio & Bluetooth after booting

## Enable Bluetooth
```
root@spencer-gen2:~# 
root@spencer-gen2:~# modprobe moal mod_para=nxp/wifi_mod_para.conf
root@spencer-gen2:~# dmesg |grep -i tty
[    0.000000] Kernel command line: console=ttymxc2,115200 loglevel=8 earlycon=ec_imx6q,0x30880000,115200 root=/dev/mmcblk1p3 rw rootfstype=ext4 rootwait
[    0.840325] 30860000.serial: ttymxc0 at MMIO 0x30860000 (irq = 36, base_baud = 1500000) is a IMX
[    0.847296] 30880000.serial: ttymxc2 at MMIO 0x30880000 (irq = 37, base_baud = 1500000) is a IMX
[    0.855692] printk: console [ttymxc2] enabled
[    0.874758] 30890000.serial: ttymxc1 at MMIO 0x30890000 (irq = 38, base_baud = 5000000) is a IMX
[    1.569096] Bluetooth: RFCOMM TTY layer initialized
root@spencer-gen2:~# ifconfig mlan0 up
root@spencer-gen2:~# hciattach ttymxc1 any 115200 flow
root@spencer-gen2:~# hciconfig -a
root@spencer-gen2:~# hciconfig
hci0:	Type: Primary  Bus: UART
  BD Address: 24:CD:8D:82:CC:71  ACL MTU: 1016:5  SCO MTU: 60:12
  DOWN 
  RX bytes:707 acl:0 sco:0 events:40 errors:0
  TX bytes:448 acl:0 sco:0 commands:40 errors:0

root@spencer-gen2:~# hciconfig hci0 up
root@spencer-gen2:~# hciconfig
hci0:	Type: Primary  Bus: UART
  BD Address: 24:CD:8D:82:CC:71  ACL MTU: 1016:5  SCO MTU: 60:12
  UP RUNNING 
  RX bytes:1449 acl:0 sco:0 events:85 errors:0
  TX bytes:1231 acl:0 sco:0 commands:85 errors:0
root@spencer-gen2:~# hcitool scan
Scanning ...
  FC:03:9F:39:4C:BF	[TV] Samsung 7 Series (55)
  D8:0F:99:57:39:80	BRAVIA 4K GB
root@spencer-gen2:~# l2ping -i LAP16113 D8:0F:99:57:39:80
Ping: D8:0F:99:57:39:80 from 24:CD:8D:82:CC:71 (data size 44) ...
44 bytes from D8:0F:99:57:39:80 id 0 time 45.68ms
44 bytes from D8:0F:99:57:39:80 id 1 time 71.06ms
44 bytes from D8:0F:99:57:39:80 id 2 time 44.02ms
```

## Bluetooth-headset
```

## Testing instructions

# Sample test
```
```
* Setting GPIO pins
```
root@spencer-gen2:~# amixer sset Lineout unmute
root@spencer-gen2:~# gpioset 3 28=0
root@spencer-gen2:~# gpioset 3 29=1

```
* List out the PCM cards
```
root@spencer-gen2:~#
root@spencer-gen2:~# pactl list cards
root@spencer-gen2:~#
```
* Verify pulseaudio running in background or not
```
root@spencer-gen2:~# pulseaudio
W: [pulseaudio] main.c: This program is not intended to be run as root (unless --system is specified).
E: [pulseaudio] pid.c: Daemon already running.
E: [pulseaudio] main.c: pa_pid_file_create() failed.
root@spencer-gen2:~#
```
* Start the bluetoothctl command to pair & connect the BT headset
```
root@spencer-gen2:~#
root@spencer-gen2:~# bluetoothctl
Agent registered
[CHG] Controller 24:CD:8D:82:CC:4F Pairable: yes
[bluetooth]# power on
Changing power on succeeded
[bluetooth]#  agent on
Agent is already registered
[bluetooth]# default-agent
Default agent request successful
[bluetooth]# scan on
Discovery started
[NEW] Device 00:11:67:46:40:A3 JBL LIVE25BT
[bluetooth]# pair 00:11:67:46:40:A3
Attempting to pair with 00:11:67:46:40:A3
[CHG] Device 00:11:67:46:40:A3 Connected: yes
[CHG] Device 00:11:67:46:40:A3 Bonded: yes
[CHG] Device 00:11:67:46:40:A3 Modalias: bluetooth:v0039p1582d0305
[CHG] Device 00:11:67:46:40:A3 UUIDs: 00001108-0000-1000-8000-00805f9b34fb
[CHG] Device 00:11:67:46:40:A3 UUIDs: 0000110b-0000-1000-8000-00805f9b34fb
[CHG] Device 00:11:67:46:40:A3 UUIDs: 0000110c-0000-1000-8000-00805f9b34fb
[CHG] Device 00:11:67:46:40:A3 UUIDs: 0000110e-0000-1000-8000-00805f9b34fb
[CHG] Device 00:11:67:46:40:A3 UUIDs: 0000111e-0000-1000-8000-00805f9b34fb
[CHG] Device 00:11:67:46:40:A3 UUIDs: 00001200-0000-1000-8000-00805f9b34fb
[CHG] Device 00:11:67:46:40:A3 ServicesResolved: yes
[CHG] Device 00:11:67:46:40:A3 Paired: yes
Pairing successful
[CHG] Device 00:11:67:46:40:A3 ServicesResolved: no
[CHG] Device 00:11:67:46:40:A3 Connected: no
[bluetooth]# connect 00:11:67:46:40:A3
Attempting to connect to 00:11:67:46:40:A3
[CHG] Device 00:11:67:46:40:A3 Connected: yes
[NEW] Endpoint /org/bluez/hci0/dev_00_11_67_46_40_A3/sep1
[NEW] Transport /org/bluez/hci0/dev_00_11_67_46_40_A3/sep1/fd0
Connection successful
[DEL] Device 6A:79:91:C9:0C:91 6A-79-91-C9-0C-91
[CHG] Device 00:11:67:46:40:A3 ServicesResolved: yes
[CHG] Transport /org/bluez/hci0/dev_00_11_67_46_40_A3/sep1/fd0 Volume: 0x0054 (84)
[CHG] Transport /org/bluez/hci0/dev_00_11_67_46_40_A3/sep1/fd0 State: active
[JBL LIVE25BT]# exit
root@spencer-gen2:~#
```
* Check the attached BT Headset listed in output sink
```
root@spencer-gen2:~#
root@spencer-gen2:~# pacmd list-sinks | grep "name:"
        name: <alsa_output.platform-sound-bt-sco.mono-fallback>
        name: <alsa_output.platform-sound.stereo-fallback>
        name: <bluez_sink.00_11_67_46_40_A3.a2dp_sink>
root@spencer-gen2:~#
```
* Start playing wav file with bluez_sink of BT connected ID.
```
root@spencer-gen2:~#
root@spencer-gen2:~# paplay -p  --device=bluez_sink.00_11_67_46_40_A3.a2dp_sink /unit_tests/ASRC/audio8k16S.wav
root@spencer-gen2:~# paplay -p  --device=bluez_sink.00_11_67_46_40_A3.a2dp_sink /usr/share/sounds/alsa/Front_Center.wav
root@spencer-gen2:~# paplay -p  --device=bluez_sink.00_11_67_46_40_A3.a2dp_sink /usr/share/sounds/alsa/Noise.wav
root@spencer-gen2:~#
```
* To verify play the wav file in alsa_output (speaker) 
```
root@spencer-gen2:~#
root@spencer-gen2:~# paplay -p  --device=alsa_output.platform-sound.stereo-fallback /unit_tests/ASRC/audio8k16S.wav
root@spencer-gen2:~# paplay -p  --device=alsa_output.platform-sound.stereo-fallback /usr/share/sounds/alsa/Noise.wav
root@spencer-gen2:~#
```

