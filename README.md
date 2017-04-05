In order to run this very simple test project, you need to:

1) Clone the project, import it to Android Studio
2) Install Genymotion emulator  https://www.genymotion.com/fun-zone/
3) Create and launch some virtual device on it after installation (I have Google Nexus 7, API 22, Android 5.10)
4) In Android Studio navigate to class WiFiAddNetworkTest and run method addWiFitest1
On request, select running Genymotion device, from Connected Devices list.

P.S.
There is a local variable - testNetworkName.
Usually there is a 'built-in' network "WiredSSID". If you set testNetworkName="WiredSSID", e.g. give the emulator some existing network, test will pass.
Otherwise it will fail, because it tries to connect to non-existent network.
                             